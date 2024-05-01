package com.example.securityjwt.controller.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenDto {

    private final String token;
    private final String role;
    private final String username;

    @Builder
    public TokenDto(String token, String role, String username) {
        this.token = token;
        this.role = role;
        this.username = username;
    }

}
