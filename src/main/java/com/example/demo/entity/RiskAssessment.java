// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.Instant;

// @Entity
// public class RiskAssessment {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private Long loanRequestId;
//     private Double dtiRatio;
//     private String creditCheckStatus;
//     private Double riskScore = 0.0; // Added field for test
//     private Instant timestamp = Instant.now();

//     public RiskAssessment() {}
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
//     public Long getLoanRequestId() { return loanRequestId; }
//     public void setLoanRequestId(Long loanRequestId) { this.loanRequestId = loanRequestId; }
//     public Double getDtiRatio() { return dtiRatio; }
//     public void setDtiRatio(Double dtiRatio) { this.dtiRatio = dtiRatio; }
//     public String getCreditCheckStatus() { return creditCheckStatus; }
//     public void setCreditCheckStatus(String status) { this.creditCheckStatus = status; }
//     public Double getRiskScore() { return riskScore; }
//     public void setRiskScore(Double riskScore) { this.riskScore = riskScore; }
// }
package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_assessments")
public class RiskAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "loan_request_id")
    private LoanRequest loanRequest;
    
    @Column(name = "risk_score")
    private Double riskScore;
    
    @Column(name = "dti_ratio")
    private Double dtiRatio;
    
    @Column(name = "risk_category")
    private String riskCategory;
    
    @Column(name = "assessed_at")
    private LocalDateTime assessedAt;
    
    @PrePersist
    protected void onCreate() {
        assessedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LoanRequest getLoanRequest() { return loanRequest; }
    public void setLoanRequest(LoanRequest loanRequest) { this.loanRequest = loanRequest; }
    
    public Double getRiskScore() { return riskScore; }
    public void setRiskScore(Double riskScore) { this.riskScore = riskScore; }
    
    public Double getDtiRatio() { return dtiRatio; }
    public void setDtiRatio(Double dtiRatio) { this.dtiRatio = dtiRatio; }
    
    public String getRiskCategory() { return riskCategory; }
    public void setRiskCategory(String riskCategory) { this.riskCategory = riskCategory; }
    
    public LocalDateTime getAssessedAt() { return assessedAt; }
    public void setAssessedAt(LocalDateTime assessedAt) { this.assessedAt = assessedAt; }
}