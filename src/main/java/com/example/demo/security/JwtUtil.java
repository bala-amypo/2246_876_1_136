// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import java.util.Date;
// import java.util.Map;

// @Component
// public class JwtUtil {

//     private final String secret;
//     private final long validity;

//     public JwtUtil(
//             @Value("${jwt.secret}") String secret,
//             @Value("${jwt.validity}") long validity
//     ) {
//         this.secret = secret;
//         this.validity = validity;
//     }

//     public String generateToken(Map<String, Object> claims, String subject) {
//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(subject)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + validity))
//                 .signWith(SignatureAlgorithm.HS256, secret)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             getAllClaims(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public Claims getAllClaims(String token) {
//         return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//     }

//     public String getEmail(String token) {
//         return getAllClaims(token).getSubject();
//     }

//     public String getRole(String token) {
//         return getAllClaims(token).get("role", String.class);
//     }
// }
package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private final SecretKey key;
    private final long expiration;
    
    public JwtUtil() {
        this("ChangeThisSecretForProductionButKeepItLongEnough", 3600000);
    }
    
    public JwtUtil(String secret, long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }
    
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }
    
    public Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    
    public String getSubject(String token) {
        return getAllClaims(token).getSubject();
    }
    
    public boolean isTokenExpired(String token) {
        return getAllClaims(token).getExpiration().before(new Date());
    }
}