package com.delivery.history.fw.response;

import lombok.Builder;

/**
 * <p>
 * API 공통 응답 데이터 바디 부분 (DTO)
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
public record CMSuccessResponse<T>(String successYn, String respMsg, T respData) {

    @Builder
    public CMSuccessResponse {

    }

}
