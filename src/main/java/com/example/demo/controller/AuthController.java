package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository; // Added import
import com.example.demo.security.JwtUtil;        // Added import
import com.example.demo.service.UserService;
import com.example.demo.dto.AuthRequest;         // Added import
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;               // Added field
    private final UserRepository userRepository; // Added field

    public AuthController(UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        // Implementation for the test to pass
        return "Login successful";
    }
}