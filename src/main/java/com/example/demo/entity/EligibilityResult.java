package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class EligibilityResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double maxEligibleAmount;

    @OneToOne
    private LoanRequest loanRequest;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getMaxEligibleAmount() { return maxEligibleAmount; }
    public void setMaxEligibleAmount(Double maxEligibleAmount) { this.maxEligibleAmount = maxEligibleAmount; }

    public LoanRequest getLoanRequest() { return loanRequest; }
    public void setLoanRequest(LoanRequest loanRequest) { this.loanRequest = loanRequest; }
}
