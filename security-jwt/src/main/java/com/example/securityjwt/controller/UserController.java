package com.example.securityjwt.controller;

import com.example.securityjwt.auth.CurrentUser;
import com.example.securityjwt.controller.dto.UserDto;
import com.example.securityjwt.model.JwtAuthentication;
import com.example.securityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id,@CurrentUser JwtAuthentication authentication) {
        String name = authentication.getName();
        System.out.println("name = " + name);
        System.out.println("isAuthenticated = " + authentication.isAuthenticated());
        System.out.println("getUsername = " + authentication.getUsername());
        System.out.println("getAuthorities = " + authentication.getAuthorities().stream().toString());
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }



}
