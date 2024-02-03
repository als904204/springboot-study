package com.example.springsecurityoauth2session.config;

import com.example.springsecurityoauth2session.service.CustomOAuth2Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2Service customOAuth2Service;

    public SecurityConfig(CustomOAuth2Service customOAuth2Service) {
        this.customOAuth2Service = customOAuth2Service;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf((csrf) -> csrf.disable());
        http
            .formLogin((login) -> login.disable());
        http
            .httpBasic((basic) -> basic.disable());
        http
            .oauth2Login((oauth2) -> oauth2
                .loginPage("/customLogin")
                .userInfoEndpoint(config ->
                    config.userService(customOAuth2Service)));
        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/oauth2/**", "/customLogin/**").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }
}
