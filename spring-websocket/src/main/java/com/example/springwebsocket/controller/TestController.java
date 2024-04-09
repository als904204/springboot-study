package com.example.springwebsocket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String[]> test() {
        int size = 1_000_000;
        String[] s = new String[size];
        for (int i = 0; i < size; i++) {
            s[i] = "a";
        }
        return ResponseEntity.ok(s);
    }
}
