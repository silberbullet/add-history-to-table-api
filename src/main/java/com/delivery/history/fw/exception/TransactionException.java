package com.delivery.history.fw.exception;

import org.springframework.http.HttpStatus;

public class TransactionException extends RuntimeException {

    private String errorCd;

    public TransactionException(String message) {
        super(message);
        this.errorCd = String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value());
    }

    public String getErrorCd() {
        return errorCd;
    }
}
