package com.example.securityjwt.common.exception;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;
    private List<FieldError> fieldErrors;


    public static ErrorResponse of(HttpStatus httpStatus,String message) {
        return ErrorResponse.builder()
            .status(httpStatus.value())
            .message(message)
            .build();
    }

    public static ErrorResponse of(HttpStatus httpStatus, List<FieldError> errors) {
        return ErrorResponse.builder()
            .status(httpStatus.value())
            .message(httpStatus.getReasonPhrase())
            .fieldErrors(errors)
            .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        public static FieldError of(org.springframework.validation.FieldError fieldError) {
            return FieldError.builder()
                .field(fieldError.getField())
                .value(String.valueOf(fieldError.getRejectedValue()))
                .reason(fieldError.getDefaultMessage())
                .build();
        }
    }
}
