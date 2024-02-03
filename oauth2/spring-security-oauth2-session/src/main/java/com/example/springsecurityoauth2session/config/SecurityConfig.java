package com.example.springsecurityoauth2session.config;

import com.example.springsecurityoauth2session.oauth2.CustomClientRegistrationRepository;
import com.example.springsecurityoauth2session.service.CustomOAuth2Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2Service customOAuth2Service;
    private final CustomClientRegistrationRepository clientRegistrationRepository;

    public SecurityConfig(CustomOAuth2Service customOAuth2Service,
        CustomClientRegistrationRepository clientRegistrationRepository) {
        this.customOAuth2Service = customOAuth2Service;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable);
        http
            .formLogin(AbstractHttpConfigurer::disable);
        http
            .httpBasic(AbstractHttpConfigurer::disable);
        http
            .oauth2Login((oauth2) -> oauth2
                .loginPage("/customLogin")
                .clientRegistrationRepository(
                    clientRegistrationRepository.clientRegistrationRepository())
                .userInfoEndpoint(config ->
                    config.userService(customOAuth2Service))
                .successHandler(new SimpleUrlAuthenticationSuccessHandler("/success")));

        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/oauth2/**", "/customLogin/**").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }
}
