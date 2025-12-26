// package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_results")
public class EligibilityResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "loan_request_id")
    private LoanRequest loanRequest;
    
    @Column(name = "max_eligible_amount")
    private Double maxEligibleAmount;
    
    @Column(name = "recommended_emi")
    private Double recommendedEmi;
    
    @Column(name = "is_eligible")
    private Boolean isEligible;
    
    @Column(name = "evaluated_at")
    private LocalDateTime evaluatedAt;
    
    @PrePersist
    protected void onCreate() {
        evaluatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LoanRequest getLoanRequest() { return loanRequest; }
    public void setLoanRequest(LoanRequest loanRequest) { this.loanRequest = loanRequest; }
    
    public Double getMaxEligibleAmount() { return maxEligibleAmount; }
    public void setMaxEligibleAmount(Double maxEligibleAmount) { this.maxEligibleAmount = maxEligibleAmount; }
    
    public Double getRecommendedEmi() { return recommendedEmi; }
    public void setRecommendedEmi(Double recommendedEmi) { this.recommendedEmi = recommendedEmi; }
    
    public Boolean getIsEligible() { return isEligible; }
    public void setIsEligible(Boolean isEligible) { this.isEligible = isEligible; }
    
    public LocalDateTime getEvaluatedAt() { return evaluatedAt; }
    public void setEvaluatedAt(LocalDateTime evaluatedAt) { this.evaluatedAt = evaluatedAt; }
}