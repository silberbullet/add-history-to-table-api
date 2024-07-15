package com.delivery.history.api.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.delivery.history.api.mapper.DvlCdMngMapper;
import com.delivery.history.api.model.req.DvlCdMngReq;
import com.delivery.history.api.model.res.DvlCdMngRes;
import com.delivery.history.fw.exception.TransactionException;

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

    @Override
    public ArrayList<DvlCdMngRes> selectList(DvlCdMngReq dvlCdMngReq) throws TransactionException {
        return dvlCdMngMapper.selectList(dvlCdMngReq);
    }

}