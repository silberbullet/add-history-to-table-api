package com.delivery.history.api.service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Service;

import com.delivery.history.api.model.req.DvlCdMngReq;
import com.delivery.history.api.model.res.DvlCdMngRes;
import com.delivery.history.api.repository.DvlCdMngRepository;
import com.delivery.history.fw.exception.BusinessException;

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
        this.checkStartDate(dvlCdMngReq);

        // 정책 유형 코드과 날짜 기준으로 판단
        DvlCdMngReq selectDvlTypeCd = new DvlCdMngReq();
        selectDvlTypeCd.setDvlTypeCd(dvlCdMngReq.getDvlTypeCd());
        selectDvlTypeCd.setStartDate(dvlCdMngReq.getStartDate());

        ArrayList<DvlCdMngRes> selectDvlTypeCdList = dvlCdMngRepository.selectList(dvlCdMngReq);

        if (!Objects.isNull(selectDvlTypeCdList)) {

        }

        return dvlCdMngRepository.insertDvlCd(dvlCdMngReq);
    }

    /**
     * 적용 날짜 유효성 체크
     */
    private void checkStartDate(DvlCdMngReq dvlCdMngReq) {

        try {
            DateTimeFormatter formmater = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate today = LocalDate.now();
            LocalDate starDate = LocalDate.parse(dvlCdMngReq.getStartDate(), formmater);

            if (!starDate.isAfter(today)) {
                throw new BusinessException("오늘 이후의 날짜를 입력해야 합니다. 입력 값 : " + dvlCdMngReq.getStartDate());
            }

        } catch (DateTimeParseException e) {
            throw new BusinessException("Invalid date format" + e.getMessage());
        }

    }
}
