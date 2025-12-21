package com.example.demo.dto;

public class AuthResponse {

    private String token;
    private Long userId;
    private String role;
    private String username;
    private String email;

    public AuthResponse() {}

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String token, Long userId, String role, String username, String email) {
        this.token = token;
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.email = email;
    }

    public String getToken() {
        return token;
    }
}
