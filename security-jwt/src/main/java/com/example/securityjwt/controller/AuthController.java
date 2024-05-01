package com.example.securityjwt.controller;


import com.example.securityjwt.controller.dto.RegisterRequest;
import com.example.securityjwt.controller.dto.TokenDto;
import com.example.securityjwt.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDto> register(@RequestBody RegisterRequest registerRequest) {
        TokenDto response = authService.register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authenticate(@RequestBody RegisterRequest registerRequest) {
        TokenDto response = authService.authenticate(registerRequest);
        return ResponseEntity.ok(response);
    }

}
