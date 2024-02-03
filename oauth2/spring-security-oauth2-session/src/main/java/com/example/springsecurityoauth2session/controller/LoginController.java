package com.example.springsecurityoauth2session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/customLogin")
    public String loginPage() {
        return "login";
    }
}
