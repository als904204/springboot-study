package com.example.securityjwt.controller.dto;


public class AuthResponse {

    private final String token;
    private final String message;

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
