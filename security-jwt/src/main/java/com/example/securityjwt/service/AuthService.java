package com.example.securityjwt.service;

import com.example.securityjwt.common.exception.CustomException;
import com.example.securityjwt.common.exception.ServerExceptionCode;
import com.example.securityjwt.common.service.TokenClockHolder;
import com.example.securityjwt.controller.dto.RegisterRequest;
import com.example.securityjwt.controller.dto.TokenDto;
import com.example.securityjwt.model.User;
import com.example.securityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenClockHolder tokenClockHolder;


    public TokenDto register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new CustomException(ServerExceptionCode.DUPLICATED);
        }

        String encodePwd = passwordEncoder.encode(request.getPassword());
        User user = User.create(request,encodePwd);
        userRepository.save(user);
        String token = jwtService.generateToken(user,tokenClockHolder);
        return new TokenDto(token, user.getRole().name(), user.getUsername());
    }

    public TokenDto authenticate(RegisterRequest request) {


        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new CustomException(ServerExceptionCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
          throw new CustomException(ServerExceptionCode.FORBIDDEN);
        }

        String token = jwtService.generateToken(user, tokenClockHolder);
        return new TokenDto(token, user.getRole().name(),user.getUsername());
    }





}
