package com.delivery.history.fw.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private String errorCd;

    public BusinessException(String message) {
        super(message);
        this.errorCd = String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    public String getErrorCd() {
        return errorCd;
    }
}
