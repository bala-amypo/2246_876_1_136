// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.Instant;

// @Entity
// public class LoanRequest {
//     // Add these static constants for test access
//     public static final String PENDING = "PENDING";
//     public static final String APPROVED = "APPROVED";
//     public static final String REJECTED = "REJECTED";
//     public static final String Status = PENDING; // To fix static reference error

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     @ManyToOne
//     private User user;
//     private Double requestedAmount;
//     private Integer tenureMonths;
//     private String purpose;
//     private String currentStatus = Status; 
//     private Instant appliedAt = Instant.now();

//     public LoanRequest() {}
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
//     public User getUser() { return user; }
//     public void setUser(User user) { this.user = user; }
//     public Double getRequestedAmount() { return requestedAmount; }
//     public void setRequestedAmount(Double requestedAmount) { this.requestedAmount = requestedAmount; }
//     public Integer getTenureMonths() { return tenureMonths; }
//     public void setTenureMonths(Integer tenureMonths) { this.tenureMonths = tenureMonths; }
//     public String getPurpose() { return purpose; }
//     public void setPurpose(String purpose) { this.purpose = purpose; }
//     public String getStatus() { return currentStatus; }
//     public void setStatus(String status) { this.currentStatus = status; }
//     public Instant getSubmittedAt() { return appliedAt; } 
// }
package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_requests")
public class LoanRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "requested_amount")
    private Double requestedAmount;
    
    @Column(name = "tenure_months")
    private Integer tenureMonths;
    
    @Column(nullable = false)
    private String status = Status.PENDING.name();
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
    }
    
    public enum Status {
        PENDING, APPROVED, REJECTED
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Double getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(Double requestedAmount) { this.requestedAmount = requestedAmount; }
    
    public Integer getTenureMonths() { return tenureMonths; }
    public void setTenureMonths(Integer tenureMonths) { this.tenureMonths = tenureMonths; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
}