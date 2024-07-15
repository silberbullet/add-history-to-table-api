package com.delivery.history.api.service;

import java.util.ArrayList;

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

        return dvlCdMngRepository.selectList(dvlCdMngReq);
    }

}
