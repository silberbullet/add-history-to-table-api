package com.delivery.history.fw.response;

import java.time.LocalDateTime;

import lombok.Builder;

/**
 * <p>
 * API 공통 에러 응답 데이터 (DTO)
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
public record CMErrorResponse(String successYn, String respCd, String msg, LocalDateTime timeStamp) {

    @Builder
    public CMErrorResponse {

    }
}
