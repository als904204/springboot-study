package com.example.securityjwt.controller;

import com.example.securityjwt.common.exception.CustomException;
import com.example.securityjwt.common.exception.ServerExceptionCode;
import com.example.securityjwt.controller.dto.TestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/user-not-found")
    public ResponseEntity<?> testUserNotFound() {
        throw new CustomException(ServerExceptionCode.USER_NOT_FOUND);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> testValidateRequest(@Valid @RequestBody TestDto testDto) {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/internal")
    public ResponseEntity<?> testInternalServerError() {
        throw new IllegalArgumentException("예상치 못한 에러 발생");
    }

}
