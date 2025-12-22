package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // MUST be at least 32 characters for HS256
    private static final String SECRET_KEY =
            "LoanEligibilitySecretKey123456789012345";

    private static final long EXPIRATION_TIME = 86400000; // 1 day

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // ======================
    // Generate Token
    // ======================
    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ======================
    // Extract Username
    // ======================
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // ======================
    // Validate Token
    // ======================
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ======================
    // Parse Claims
    // ======================
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
