package org.study.springaop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.study.springaop.business.BusinessService1;

@SpringBootApplication
public class SpringAopApplication implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final BusinessService1 businessService1;

    public SpringAopApplication(BusinessService1 businessService1) {
        this.businessService1 = businessService1;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Value returned is {}", businessService1.calMax());
    }

}

