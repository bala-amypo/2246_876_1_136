package com.example.demo.dto;

public class AuthResponse {
    private String token;

    // Default Constructor
    public AuthResponse() {}

    // Constructor with arguments
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    // Setter
    public void setToken(String token) {
        this.token = token;
    }
}