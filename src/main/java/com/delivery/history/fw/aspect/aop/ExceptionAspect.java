package com.delivery.history.fw.aspect.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.delivery.history.fw.exception.BusinessException;
import com.delivery.history.fw.exception.TransactionException;

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
public class ExceptionAspect {

    @AfterThrowing(pointcut = "com.delivery.history.fw.aspect.pointcut.CommonPointcuts.inDataAcessoLayer()", throwing = "ex")
    public void createTransactionException(DataAccessException ex) {

        throw new TransactionException("(Transaction Error) " + ex.getClass().getName() + " : " + ex.getMessage());
    }

    @AfterThrowing(pointcut = "com.delivery.history.fw.aspect.pointcut.CommonPointcuts.inBusinessLayer() && !com.delivery.history.fw.aspect.pointcut.CommonPointcuts.inDataAcessoLayer()", throwing = "ex")
    public void createBusinessException(RuntimeException ex) {

        if (ex instanceof TransactionException) {
            throw ex;
        } else {
            throw new BusinessException(
                    "(Service Error) " + ex.getClass().getName() + " : " + ex.getLocalizedMessage());
        }
    }
}
