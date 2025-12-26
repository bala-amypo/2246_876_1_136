package com.example.demo.controller;

import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        // For demo, accept any username/password
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER"); // Example custom claim

        // Generate JWT token
        String token = jwtUtil.generateToken(claims, username);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
