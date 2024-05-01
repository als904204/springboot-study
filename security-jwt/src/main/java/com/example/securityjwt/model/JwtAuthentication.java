package com.example.securityjwt.model;

import com.example.securityjwt.controller.dto.TokenDto;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
@Getter
public class JwtAuthentication implements Authentication {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthentication.class);

    private List<GrantedAuthority> authorities;
    private String username;
    private boolean isAuthenticated;

    public JwtAuthentication(TokenDto dto) {
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(dto.getRole()));
        this.username = dto.getUsername();
        this.isAuthenticated = true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return username;
    }
}
