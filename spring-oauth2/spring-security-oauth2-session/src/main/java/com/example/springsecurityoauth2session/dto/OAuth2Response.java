package com.example.springsecurityoauth2session.dto;

public interface OAuth2Response {

    // naver,google
    String getProvider();

    // 1231423
    String getProviderId();

    String getEmail();

    String getName();


}
