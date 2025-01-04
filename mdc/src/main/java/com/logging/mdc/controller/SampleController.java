package com.logging.mdc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class SampleController {

    @GetMapping("/hello")
    public String hello() {
        // MDC에 저장된 correlationId가 로깅 패턴을 통해 자동 출력됨
        log.info("Hello API called");
        return "Hello, World!";
    }

    @PostMapping("/order")
    public String createOrder(@RequestBody String orderInfo) {
        log.info("Order created: {}", orderInfo);
        return "Order processed: " + orderInfo;
    }
}