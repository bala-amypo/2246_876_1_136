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
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public org.springframework.http.ResponseEntity<com.example.demo.dto.AuthResponse> login(@RequestBody com.example.demo.dto.AuthRequest authRequest) {
        User user = userService.findByEmail(authRequest.getEmail());
        String token = jwtUtil.generateToken(new java.util.HashMap<>(), user.getEmail());
        return org.springframework.http.ResponseEntity.ok(new com.example.demo.dto.AuthResponse(
                token, user.getId(), user.getEmail(), user.getRole(), user.getFullName()
        ));
    }
}