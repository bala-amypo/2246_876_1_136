package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.service.AuthService;
import com.example.demo.entity.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestParam String fullName,
                         @RequestParam String email,
                         @RequestParam String password) {
        return authService.register(fullName, email, password);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email,
                      @RequestParam String password) {
        return authService.login(email, password);
    }
}
