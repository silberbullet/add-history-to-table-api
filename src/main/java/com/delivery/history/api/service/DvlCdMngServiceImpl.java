package com.delivery.history.api.service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.net.IDN;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.delivery.history.api.model.req.DvlCdMngReq;
import com.delivery.history.api.model.res.DvlCdMngRes;
import com.delivery.history.api.repository.DvlCdMngRepository;
import com.delivery.history.fw.exception.BusinessException;
import com.delivery.history.fw.utils.DateUtil;
import com.delivery.history.fw.utils.DvlConstants;

import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 배송비 정책 관리를 위한 서비스 클래스
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
@Service
@RequiredArgsConstructor
public class DvlCdMngServiceImpl implements DvlCdMngService {

    private final DvlCdMngRepository dvlCdMngRepository;

    /**
     * 배송비 정책 내역 조회
     */
    @Override
    public ArrayList<DvlCdMngRes> selectList(DvlCdMngReq dvlCdMngReq) throws BusinessException {

        if (Objects.isNull(dvlCdMngReq.getUseYn())) {
            dvlCdMngReq.setUseYn("Y");
        }

        return dvlCdMngRepository.selectList(dvlCdMngReq);
    }

    /**
     * 배송비 정책 등록
     */
    @Override
    public DvlCdMngRes insertDvlCd(DvlCdMngReq dvlCdMngReq) throws BusinessException {

        // 값 유효성 체크 ( 오늘 날짜 보다 이후 인가 )
        checkStartDate(dvlCdMngReq);

        // 정책 유형 코드과 날짜 기준으로 판단
        DvlCdMngReq selectDvlTypeCd = new DvlCdMngReq().setDvlTypeCd(dvlCdMngReq.getDvlTypeCd())
                .setStartDate(dvlCdMngReq.getStartDate());

        ArrayList<DvlCdMngRes> dvlCdList = dvlCdMngRepository.selectList(selectDvlTypeCd);

        // 고려야해야 할 배송비 정책 존재 시 검증 처리
        if (!dvlCdList.isEmpty()) {
            validateDvlTypeCd(dvlCdList, dvlCdMngReq);
        }

        if (Objects.isNull(dvlCdMngReq.getEndDate())) {
            dvlCdMngReq.setEndDate(DvlConstants.DEFAULT_END_DATE);
        }

        return dvlCdMngRepository.insertDvlCd(dvlCdMngReq);
    }

    /**
     * 적용 날짜 유효성 체크
     */
    private void checkStartDate(DvlCdMngReq dvlCdMngReq) {

        try {
            LocalDate today = LocalDate.now();
            LocalDate starDate = DateUtil.parseDate(dvlCdMngReq.getStartDate());

            if (!starDate.isAfter(today)) {
                throw new BusinessException("오늘 이후의 날짜를 입력해야 합니다. 입력 값 : " + dvlCdMngReq.getStartDate());
            }

        } catch (DateTimeParseException e) {
            throw new BusinessException("Invalid date format" + e.getMessage());
        }

    }

    /**
     * 배송비 정책 목록 검증 처리
     */
    private void validateDvlTypeCd(ArrayList<DvlCdMngRes> dvlCdList, DvlCdMngReq dvlCdMngReq) {

        try {
            // 신규 정책 적용 날짜
            LocalDateTime newDvlCdStartTime = DateUtil.getDateTime(dvlCdMngReq.getStartDate());

            // 동일한 적용 날짜가 존재한다면 사용여부 N 처리
            if (newDvlCdStartTime.isEqual(DateUtil.parseDateTime(dvlCdList.get(0).getStartDate()))) {
                updateInvalidPolicies(dvlCdList.get(0));
                dvlCdList.remove(0);
            }

            // 정책 목록 중 신규 적용 날짜 보다 최초로 날짜가 미래인 정책 탐색
            dvlCdList.stream()
                    .filter(dvlCd -> newDvlCdStartTime.isBefore(DateUtil.parseDateTime(dvlCd.getStartDate())))
                    .findFirst()
                    .ifPresentOrElse(
                            // 최초 미래인 정책을 찾은 경우
                            (dvlCd) -> {
                                LocalDateTime startdate = DateUtil.parseDateTime(dvlCd.getStartDate());
                                dvlCdMngReq.setEndDate(DateUtil.formatDateTime(startdate.minusSeconds(1)));
                                // 전 인덱스가 존재 시, 종료 날짜 update
                                int index = dvlCdList.indexOf(dvlCd);
                                if (index - 1 >= 0) {
                                    updateOldDvlCd(dvlCdList.get(index - 1),
                                            newDvlCdStartTime.minusSeconds(1));
                                }
                            },
                            // 가장 미래인 정책으로 판정
                            () -> {
                                dvlCdMngReq.setEndDate(DvlConstants.DEFAULT_END_DATE);
                                // 마지막 인덱스는 -1초 처리
                                updateOldDvlCd(dvlCdList.get(dvlCdList.size() - 1), newDvlCdStartTime.minusSeconds(1));
                            });

        } catch (DateTimeParseException e) {
            throw new BusinessException("Invalid date format" + e.getMessage());
        }
    }

    /**
     * 기존 정책 종료 날짜 Update
     */
    private void updateOldDvlCd(DvlCdMngRes dvlCdMngRes, LocalDateTime localDateTime) {
        DvlCdMngReq dvlCdMngReq = new DvlCdMngReq();

        BeanUtils.copyProperties(dvlCdMngRes, dvlCdMngReq);

        dvlCdMngReq.setEndDate(DateUtil.formatDateTime(localDateTime));

        dvlCdMngRepository.updateDvlCd(dvlCdMngReq);
    }

    /**
     * 기존 정책 사용 여부 N Update
     */
    private void updateInvalidPolicies(DvlCdMngRes dvlCdMngRes) {
        DvlCdMngReq dvlCdMngReq = new DvlCdMngReq();

        BeanUtils.copyProperties(dvlCdMngRes, dvlCdMngReq);

        dvlCdMngReq.setUseYn("N");

        dvlCdMngRepository.updateDvlCd(dvlCdMngReq);
    }
}
