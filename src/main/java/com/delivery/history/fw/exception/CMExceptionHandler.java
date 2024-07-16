package com.delivery.history.fw.exception;

import java.time.LocalDateTime;

import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
@Order(1)
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CMErrorResponse processValidationError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }

        return CMErrorResponse.builder()
                .successYn("N")
                .respCd(String.valueOf(e.getStatusCode().value()))
                .msg(builder.toString())
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
