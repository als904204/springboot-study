package com.logging.mdc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class NoThreadLocalServiceTest {

    private static final Logger log = LoggerFactory.getLogger(NoThreadLocalServiceTest.class);

    private NoThreadLocalService service = new NoThreadLocalService();

    @Test
    void test() throws InterruptedException {
        log.info("main start");

        Runnable userA = () -> service.logic("userA");

        Runnable userB = () -> service.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);
        threadB.start();

        threadA.join();
        threadB.join();

        log.info("main exit....!");

    }

    private void sleep(int m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}