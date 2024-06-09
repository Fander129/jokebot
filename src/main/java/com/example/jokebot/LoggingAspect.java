package com.example.jokebot;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.example.jokebot.controller..*)")
    public void controllerPointcut() {}

    @Pointcut("within(com.example.jokebot.service..*)")
    public void servicePointcut() {}

    @Before("controllerPointcut() || servicePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Method invoked: {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "controllerPointcut() || servicePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method completed: {}", joinPoint.getSignature().toShortString());
    }
}
