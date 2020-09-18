package com.meli.challenge.items.metrics;

import com.meli.challenge.items.service.HealthService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetricsAspect {

    private static final Logger logger = LoggerFactory.getLogger(MetricsAspect.class);

    @Autowired
    private HealthService healthService;

    @Around("@annotation(MethodExecutionTime)")
    public Object methodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        healthService.registerApiCall(start, System.currentTimeMillis());

        return proceed;
    }

}
