 package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.Instant;

// @Entity
// @Table(name = "users")
// public class User {
//     // Add these static constants for test access
//     public static final String CUSTOMER = "CUSTOMER";
//     public static final String ADMIN = "ADMIN";
//     public static final String Role = CUSTOMER; // To fix static reference error

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private String fullName;
//     @Column(nullable = false, unique = true)
//     private String email;
//     private String password;
//     private String userRole = Role; 
//     private Instant createdAt = Instant.now();

//     public User() {}
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
//     public String getFullName() { return fullName; }
//     public void setFullName(String fullName) { this.fullName = fullName; }
//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }
//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }
//     public String getRole() { return userRole; }
//     public void setRole(String role) { this.userRole = role; }
// }


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String fullName;
    
    @Column(nullable = false)
    private String role = Role.CUSTOMER.name();
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    public enum Role {
        CUSTOMER, ADMIN
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}