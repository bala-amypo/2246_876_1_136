package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
public class EligibilityResult {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_request_id")
    private LoanRequest loanRequest;

    private Double maxEligibleAmount;
    private Double estimatedEmi;
    private String riskLevel;
    
    @CreationTimestamp
    private Timestamp calculatedAt;

    // Constructors
    public EligibilityResult() {}

    public EligibilityResult(Long id, LoanRequest loanRequest, Double maxEligibleAmount, Double estimatedEmi, String riskLevel, Timestamp calculatedAt) {
        this.id = id;
        this.loanRequest = loanRequest;
        this.maxEligibleAmount = maxEligibleAmount;
        this.estimatedEmi = estimatedEmi;
        this.riskLevel = riskLevel;
        this.calculatedAt = calculatedAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LoanRequest getLoanRequest() { return loanRequest; }
    public void setLoanRequest(LoanRequest loanRequest) { this.loanRequest = loanRequest; }

    public Double getMaxEligibleAmount() { return maxEligibleAmount; }
    public void setMaxEligibleAmount(Double maxEligibleAmount) { this.maxEligibleAmount = maxEligibleAmount; }

    public Double getEstimatedEmi() { return estimatedEmi; }
    public void setEstimatedEmi(Double estimatedEmi) { this.estimatedEmi = estimatedEmi; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public Timestamp getCalculatedAt() { return calculatedAt; }
    public void setCalculatedAt(Timestamp calculatedAt) { this.calculatedAt = calculatedAt; }
}