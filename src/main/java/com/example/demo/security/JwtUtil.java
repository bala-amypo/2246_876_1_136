package com.example.demo.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    // MUST BE PUBLIC: Test calls this directly (Image 7)
    public Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
    }
}