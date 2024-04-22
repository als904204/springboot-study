package com.example.securityjwt.service;

import com.example.securityjwt.common.service.TokenClockHolder;
import com.example.securityjwt.controller.dto.AuthRequest;
import com.example.securityjwt.controller.dto.AuthResponse;
import com.example.securityjwt.model.User;
import com.example.securityjwt.repository.UserRepository;
import com.example.securityjwt.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenClockHolder tokenClockHolder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
        JwtService jwtService, TokenClockHolder tokenClockHolder,
        AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenClockHolder = tokenClockHolder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(AuthRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already exists!");
        }

        String encodePwd = passwordEncoder.encode(request.getPassword());
        User user = User.create(request,encodePwd);
        userRepository.save(user);

        String token = jwtService.generateToken(request,tokenClockHolder);
        return new AuthResponse(token, "User registration was successful");
    }

    public AuthResponse authenticate(AuthRequest request) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("User Not Found!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
          throw new IllegalArgumentException("User authentication failed!");
        }

        String token = jwtService.generateToken(request, tokenClockHolder);
        return new AuthResponse(token, "User authentication success");
    }





}
