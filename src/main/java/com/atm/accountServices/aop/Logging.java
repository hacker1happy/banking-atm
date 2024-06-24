package com.atm.accountServices.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before(value = "execution(* com.atm.accountServices..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before entering method: {} with arguments: {} ,Class Name:{}", joinPoint.getSignature().getName(), joinPoint.getArgs(), joinPoint.getSignature().getDeclaringTypeName());
    }

    @After(value = "execution(* com.atm.accountServices..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("After executing method: {} with arguments: {} ,Class Name:{}", joinPoint.getSignature().getName(), joinPoint.getArgs(), joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterThrowing(value = "execution(* com.atm.accountServices..*(..))", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex)
    {
        logger.error("After Throwing exception in method: {} with arguments: {} ,Class Name:{}", joinPoint.getSignature().getName(), joinPoint.getArgs(), joinPoint.getSignature().getDeclaringTypeName());
        logger.error("Exception is: {}",ex.getMessage());
    }
}
