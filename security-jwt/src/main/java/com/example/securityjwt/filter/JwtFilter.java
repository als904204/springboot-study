package com.example.securityjwt.filter;


import com.example.securityjwt.common.exception.CustomException;
import com.example.securityjwt.common.util.ResponseUtils;
import com.example.securityjwt.controller.dto.TokenDto;
import com.example.securityjwt.model.JwtAuthentication;
import com.example.securityjwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String bearerToken = request.getHeader("Authorization");

        if (bearerToken == null ||!bearerToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = bearerToken.substring(7);

        try {

            TokenDto tokenDto = jwtService.parseToken(token);
            Authentication jwtAuthentication = new JwtAuthentication(tokenDto);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
            
        } catch (CustomException e) {
            ResponseUtils.setErrorResponse(response, e.getExceptionCode().getStatus(),
                e.getExceptionCode());
            return;
        }

        filterChain.doFilter(request, response);
    }


    private Authentication getAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());

    }



}
