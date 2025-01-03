package com.logging.mdc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadLocalExample {


    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalExample.class);
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            threadLocal.set("user123");
            simulateWork("Thread1");
            threadLocal.remove();
        }).start();

        new Thread(() -> {
            threadLocal.set("user456");
            simulateWork("Thread2");
            threadLocal.remove();
        }).start();


    }

    private static void simulateWork(String threadName) {
        // 스레드 로컬에서 사용자 ID 가져오기
        String userId = threadLocal.get();
        logger.info("{}: Working for user {}", threadName, userId);
    }

}
