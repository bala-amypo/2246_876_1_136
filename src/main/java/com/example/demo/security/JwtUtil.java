package com.example.demo.security;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

@Component // Ensures this is registered as a bean
public class JwtUtil {
    // Standardize your methods here
    public Claims getAllClaims(String token) {
        // Implementation logic
        return null; 
    }
}