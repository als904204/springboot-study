package com.logging.mdc.service;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class YesThreadLocalServiceTest {

    private static final Logger log = LoggerFactory.getLogger(YesThreadLocalServiceTest.class);

    private YesThreadLocalService service = new YesThreadLocalService();

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