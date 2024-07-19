package com.delivery.history.fw.aspect.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 서비스, DAO 패키지에 에러 핸들링을 다루기 위한 AOP
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("com.delivery.history.fw.aspect.pointcut.CommonPointcuts.inWebLayer()")
    public Object checkApiTime(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = pjp.proceed();

        long endTime = System.currentTimeMillis();

        long wating = endTime - startTime;
        log.info("---------------------------------------------");
        log.info("Api Response Time : " + wating + "ms");

        return result;
    }

}
