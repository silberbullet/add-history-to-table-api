package com.delivery.history.fw.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>
 * RestController 단에 나오는 공통 응답 데이터 처리 클래스
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
@RestControllerAdvice
public class CMResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // RestController 응답에서 공통 처리를 하기 위해 true 설정
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.OK);
        // 예외 처리 데이터
        if (body instanceof CMErrorResponse cmErrorResponse) {
            return cmErrorResponse;
        }
        // 성공 처리 데이터
        return CMSuccessResponse.builder()
                .successYn("Y")
                .respData(body)
                .respMsg("정상 처리 되었습니다.")
                .build();

    }

}
