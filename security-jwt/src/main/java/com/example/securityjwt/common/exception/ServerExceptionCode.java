package com.example.securityjwt.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServerExceptionCode implements ExceptionCode {

    USER_NOT_FOUND(404, "User Not Found"),
    FORBIDDEN(403, "Forbidden"),
    DUPLICATED(409, "Duplicated Username"),
    BAD_REQUEST(400,"Bad Request"),
    EXPIRED_TOKEN(401,"Expired JWT token"),
    INVALID_TOKEN(403,"Invalid JWT token"),
    INTERNAL_SERVER_ERROR(500,"Internal Server Error");

    private final int status;
    private final String message;

}