package com.example.securityjwt.controller.dto;

import com.example.securityjwt.model.Role;
import com.example.securityjwt.model.User;
import lombok.Getter;

@Getter
public class UserDto {

    private final String username;
    private final Role role;
    public UserDto(User user) {
        this.username = user.getUsername();
        this.role = user.getRole();
    }

}
