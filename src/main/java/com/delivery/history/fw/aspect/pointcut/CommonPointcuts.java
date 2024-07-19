package com.delivery.history.fw.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

/**
 * <p>
 * Aop Advice의 PointCut 모음 클래스
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
public class CommonPointcuts {

    /**
     * Business Layer Aop의 PointCut,"service" 패키지 적용
     */
    @Pointcut("execution(* com.delivery.history.api.controller.*Controller.*(..))")
    public void inWebLayer() {

    }

    /**
     * Business Layer Aop의 PointCut,"service" 패키지 적용
     */
    @Pointcut("execution(* com.delivery.history.api.service.*.*(..))")
    public void inBusinessLayer() {

    }

    /**
     * Dao Layer Aop의 PointCut,"repository" 패키지 적용
     */
    @Pointcut("execution(* com.delivery.history.api.repository.*.*(..))")
    public void inDataAcessoLayer() {

    }
}
