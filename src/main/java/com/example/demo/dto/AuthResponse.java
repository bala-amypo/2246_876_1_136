 package com.example.demo.dto;

// public class AuthResponse {
//     private String token;
//     private Long userId;
//     private String email;
//     private String role;
//     private String fullName;

//     public AuthResponse(String token, Long userId, String email, String role, String fullName) {
//         this.token = token;
//         this.userId = userId;
//         this.email = email;
//         this.role = role;
//         this.fullName = fullName;
//     }
//     // Getters and Setters
//     public String getToken() { return token; }
//     public Long getUserId() { return userId; }
//     public String getEmail() { return email; }
//     public String getRole() { return role; }
//     public String getFullName() { return fullName; }
// }


public class AuthResponse {
    private String token;
    private String email;
    private String role;
    
    public AuthResponse() {}
    
    public AuthResponse(String token, String email, String role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}