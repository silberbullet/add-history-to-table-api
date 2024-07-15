package com.delivery.history.fw.exception;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.delivery.history.fw.response.CMErrorResponse;

/**
 * <p>
 * gyeongwooPark 처리
 * </p>
 *
 * @author
 * @version 1.0
 * @since 2024.07
 */
@RestControllerAdvice
public class CMExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected CMErrorResponse businessExceptionHandler(BusinessException e) {

        return CMErrorResponse.builder()
                .successYn("N")
                .respCd(e.getErrorCd())
                .msg(e.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(TransactionException.class)
    protected CMErrorResponse transactionExceptionHandler(TransactionException e) {

        return CMErrorResponse.builder()
                .successYn("N")
                .respCd(e.getErrorCd())
                .msg(e.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
