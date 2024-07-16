package com.delivery.history.api.repository;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.delivery.history.api.mapper.DvlCdMngMapper;
import com.delivery.history.api.model.req.DvlCdMngReq;
import com.delivery.history.api.model.res.DvlCdMngRes;
import com.delivery.history.fw.exception.TransactionException;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 배송비 정책 관리를 위한 DAO 구현체
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
@Repository
@RequiredArgsConstructor
public class DvlCdMngRepositoryImpl implements DvlCdMngRepository {

    private final DvlCdMngMapper dvlCdMngMapper;

    /**
     * 배송비 정책 내역 조회
     */
    @Override
    public ArrayList<DvlCdMngRes> selectList(DvlCdMngReq dvlCdMngReq) throws TransactionException {
        return dvlCdMngMapper.selectList(dvlCdMngReq);
    }

    /**
     * 배송비 정책 등록
     */
    @Override
    public DvlCdMngRes insertDvlCd(DvlCdMngReq dvlCdMngReq) {

        // selectKey 동시성을 방지 하기 위한 DVL_CD 제너레이션
        String newDvlCd = dvlCdMngMapper.getDvlCdKey();

        dvlCdMngReq.setDvlCd(newDvlCd);

        dvlCdMngMapper.insertDvlCd(dvlCdMngReq);

        return null;
    }
}