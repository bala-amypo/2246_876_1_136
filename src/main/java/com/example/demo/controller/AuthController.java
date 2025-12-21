package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestParam String username) {
        String token = jwtUtil.generateToken(username);
        return new AuthResponse(token);
    }
}
