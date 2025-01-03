package com.logging.mdc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MDCExample {

    private static final Logger logger = LoggerFactory.getLogger(MDCExample.class);

    public static void main(String[] args) {
        new Thread(() -> {
            MDC.put("userId", "user123");
            simulateWork("Thread1");
            MDC.clear();  // 사용 후 MDC를 정리
        }).start();

        new Thread(() -> {
            MDC.put("userId", "user456");
            simulateWork("Thread2");
            MDC.clear();  // 사용 후 MDC를 정리
        }).start();
    }

    private static void simulateWork(String threadName) {
        String userId = MDC.get("userId");
        // 로그 메시지에 자동으로 'userId'가 포함됩니다.
        logger.info("{}: Working for user >> {}", threadName, userId);
    }

}
