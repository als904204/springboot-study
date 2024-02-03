package com.example.springsecurityoauth2session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf((csrf) -> csrf.disable());
        http
            .formLogin((login) -> login.disable());
        http
            .httpBasic((basic) -> basic.disable());
        http
            .oauth2Login(Customizer.withDefaults()); // OAuth2 필터들을 기본 값으로 설정(기본값으로 안하면 직접 다 구현 해야함)
        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }
}
