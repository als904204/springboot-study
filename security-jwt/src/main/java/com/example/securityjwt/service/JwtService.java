package com.example.securityjwt.service;

import com.example.securityjwt.common.exception.CustomException;
import com.example.securityjwt.common.exception.ServerExceptionCode;
import com.example.securityjwt.common.service.TokenClockHolder;
import com.example.securityjwt.controller.dto.RegisterRequest;
import com.example.securityjwt.controller.dto.TokenDto;
import com.example.securityjwt.model.User;
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

    public TokenDto parseToken(String token) {
        Claims claims = Jwts
            .parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

        String username = claims.getSubject();
        String role = claims.get("role", String.class);
        return TokenDto.builder()
            .username(username)
            .role(role)
            .token(token)
            .build();
    }


    public String generateToken(User user, TokenClockHolder tokenClockHolder) {
        return Jwts
            .builder()
            .subject(user.getUsername())
            .claim("role", user.getRole().name())
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
