package com.alugaai.rentwave.lib.config;

import com.alugaai.rentwave.lib.annotations.LogExecution;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Configuration
public class AopConfig {

    @Around("@annotation(logExecution)")
    public Object logExecution(final ProceedingJoinPoint point, final LogExecution  logExecution) throws Throwable {
        final String clazz = point.getSignature().getDeclaringTypeName();
        final String method = point.getSignature().getName();

        final StopWatch sw = new StopWatch();

        log.info("START :: CLASS {} :: METHOD {} :: PARAMS {}", clazz, method, point.getArgs());
        sw.start(method);
        final Object output = point.proceed();
        sw.stop();
        log.info("END :: CLASS {} :: METHOD {} :: EXECUTION {}ms", clazz, method, sw.getTotalTimeMillis());

        return output;
    }

}
