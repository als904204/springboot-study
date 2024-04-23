package com.example.securityjwt.security;

import com.example.securityjwt.common.exception.CustomException;
import com.example.securityjwt.common.exception.ServerExceptionCode;
import com.example.securityjwt.common.service.TokenClockHolder;
import com.example.securityjwt.controller.dto.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String generateToken(AuthRequest authRequest, TokenClockHolder tokenClockHolder) {
        return Jwts
            .builder()
            .subject(authRequest.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + tokenClockHolder.jwtExpiration()))
            .signWith(getSignInKey())
            .compact();
    }


    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch (ExpiredJwtException e) {
            throw new CustomException(ServerExceptionCode.EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new CustomException(ServerExceptionCode.INVALID_TOKEN);
        }
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
