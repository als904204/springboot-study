package org.study.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(getClass());

    // when - 언제 이 로직이 실행 될건지
    // what - 무슨 로직이 실행 될건지
    // 해당하는 패키지 메소드가 실행되기 전에 가로챔
    @Before("execution(* org.study.springaop.*.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        log.info("Before Aspect - Method is called={}", joinPoint);
    }

}
