package com.sango.microservices.client.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class AspectConfiguration {
    private long startTimeInMillis;
    private long afterTimeInMillis;

    @Before(value = "execution(* com.sango.microservices.client.service.*.*(..))")
    public void beforeMethodExecution (JoinPoint joinPoint){
        startTimeInMillis = System.currentTimeMillis();
        log.info("Starting time in milli sec is: {} in method: {}",startTimeInMillis,joinPoint.getSignature());
    }

    @After(value = "execution(* com.sango.microservices.client.service.*.*(..))")
    public void afterMethodExecution (JoinPoint joinPoint){
        afterTimeInMillis = System.currentTimeMillis();
        long timeTaken = afterTimeInMillis - startTimeInMillis;
        log.info("After execution time taken in milli sec is: {} in method: {}",timeTaken,joinPoint.getSignature());
    }

    @AfterThrowing(pointcut = "execution(* com.sango.microservices.client.service.*.*(..))", throwing = "ex")
    public void afterExpecption(Exception ex) throws Throwable {
        log.error("Execption occurred with exception message as: {}", ex);
    }
}
