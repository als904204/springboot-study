package com.example.securityjwt.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenTokenClockHolder implements TokenClockHolder {

    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    public long jwtExpiration() {
        return expiration;
    }

}
