package com.example.securityjwt.common.exception;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 서버 내 예외 발생
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();
        return ResponseEntity
            .status(exceptionCode.getStatus())
            .body(ErrorResponse.builder()
                .status(exceptionCode.getStatus())
                .message(exceptionCode.getMessage())
                .build());
    }

    // 쿼리 파라미터나 폼 데이터에 필요한 항목이 없는 경우
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(
        MissingServletRequestParameterException e) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e) {
        return ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED,e.getMessage());
    }

    // Validate 예외 발생
    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBindException(BindException e) {
        List<ErrorResponse.FieldError> fieldErrors = e.getFieldErrors().stream()
            .map(ErrorResponse.FieldError::of)
            .toList();
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, fieldErrors);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {

        log.error("INTERNAL_SERVER_ERROR={}", e.getMessage());
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
