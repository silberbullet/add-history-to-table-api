package com.delivery.history.api.repository;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;

import com.delivery.history.api.model.req.DvlCdMngReq;
import com.delivery.history.api.model.res.DvlCdMngRes;
import com.delivery.history.fw.exception.BusinessException;
import com.delivery.history.fw.exception.TransactionException;

/**
 * <p>
 * 배송비 정책 관리를 위한 DAO 인터페이스
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
public interface DvlCdMngRepository {
    /**
     * 배송비 정책 내역 조회
     *
     * @param {DvlCdMngReq} dvlCdMngReq
     * @return ArrayList<DvlCdMngRes>
     */
    public ArrayList<DvlCdMngRes> selectList(DvlCdMngReq dvlCdMngReq) throws TransactionException;

    /**
     * 배송비 정책 등록
     *
     * @param {DvlCdMngReq} dvlCdMngReq
     * @return DvlCdMngRes
     */
    public DvlCdMngRes insertDvlCd(DvlCdMngReq dvlCdMngReq) throws TransactionException;
}
