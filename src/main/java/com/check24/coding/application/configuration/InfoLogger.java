package com.check24.coding.application.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InfoLogger {

    private static Log LOG = LogFactory.getLog(InfoLogger.class);

    @Around("@annotation(LogInfo)")
    public Object print(final ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("before hello");
        Object res = joinPoint.proceed();
        LOG.info("after hello");
        return res;
    }
}
