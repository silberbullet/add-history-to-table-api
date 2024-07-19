package com.delivery.history.api.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.delivery.history.api.model.req.DvlCdMngReq;
import com.delivery.history.api.model.res.DvlCdMngRes;
import com.delivery.history.api.service.DvlCdMngService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 배송비 정책 관리를 위한 Controller 클래스
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dvl-cd-mng")
public class DvlCdMngController {

    private final DvlCdMngService dvlCdMngService;

    /**
     * 배송비 정책 내역 조회
     *
     * @param {DvlCdMngReq} dvlCdMngReq
     * @return ArrayList<DvlCdMngRes>
     */
    @GetMapping("/select-list")
    public ArrayList<DvlCdMngRes> selectList(DvlCdMngReq dvlCdMngReq) {

        return dvlCdMngService.selectList(dvlCdMngReq);
    }

    /**
     * 배송비 정책 등록
     *
     * @param {DvlCdMngReq} dvlCdMngReq
     * @return String
     */
    @PostMapping("/insert-dvl-cd")
    public DvlCdMngRes insertDvlCd(@Valid @RequestBody DvlCdMngReq dvlCdMngReq) {

        DvlCdMngRes dvlCdMngRes = dvlCdMngService.insertDvlCd(dvlCdMngReq);

        return dvlCdMngRes;
    }
}
