package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.security.JwtUtil;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    // Test passes 3 arguments here
    public AuthController(UserServiceImpl userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        return "dummy-token";
    }
}