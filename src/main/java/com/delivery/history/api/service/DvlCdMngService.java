package com.delivery.history.api.service;

import java.util.ArrayList;

import com.delivery.history.api.model.req.DvlCdMngReq;
import com.delivery.history.api.model.res.DvlCdMngRes;

/**
 * <p>
 * 배송비 정책 관리를 위한 서비스 인터페이스
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
public interface DvlCdMngService {
    /**
     * 배송비 정책 내역 조회
     *
     * @param {DvlCdMngReq} dvlCdMngReq
     * @return ArrayList<DvlCdMngRes>
     */
    public ArrayList<DvlCdMngRes> selectList(DvlCdMngReq dvlCdMngReq);
}
