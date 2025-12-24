package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
public class RiskAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long loanRequestId; // Added for LoanEligibilityServiceImpl
    private Integer riskScore;
    private double dtiRatio; // Added for LoanEligibilityServiceImpl
    private String creditCheckStatus; // Added for LoanEligibilityServiceImpl

    public RiskAssessment() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getLoanRequestId() { return loanRequestId; }
    public void setLoanRequestId(Long loanRequestId) { this.loanRequestId = loanRequestId; }
    public Integer getRiskScore() { return riskScore; }
    public void setRiskScore(Integer riskScore) { this.riskScore = riskScore; }
    public double getDtiRatio() { return dtiRatio; }
    public void setDtiRatio(double dtiRatio) { this.dtiRatio = dtiRatio; }
    public String getCreditCheckStatus() { return creditCheckStatus; }
    public void setCreditCheckStatus(String creditCheckStatus) { this.creditCheckStatus = creditCheckStatus; }
}