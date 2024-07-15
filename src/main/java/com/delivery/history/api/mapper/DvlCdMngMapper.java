package com.delivery.history.api.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.delivery.history.api.model.req.DvlCdMngReq;
import com.delivery.history.api.model.res.DvlCdMngRes;
import com.delivery.history.fw.exception.TransactionException;

/**
 * <p>
 * 배송비 정책 관리를 위한 Mapper Interface
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
@Mapper
public interface DvlCdMngMapper {
    /**
     * 배송비 정책 내역 조회
     *
     * @param {DvlCdMngReq} dvlCdMngReq
     * @return ArrayList<DvlCdMngRes>
     */
    public ArrayList<DvlCdMngRes> selectList(DvlCdMngReq dvlCdMngReq) throws TransactionException;
}
