package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RiskAssessmentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long loanRequestId;

    @Column(nullable = false)
    private Double dtiRatio;

    @Column(nullable = false)
    private String creditCheckStatus;

    @Column(updatable = false)
    private LocalDateTime timestamp;

    public RiskAssessmentLog() {}

    public RiskAssessmentLog(Long loanRequestId, Double dtiRatio, String creditCheckStatus) {
        this.loanRequestId = loanRequestId;
        this.dtiRatio = dtiRatio;
        this.creditCheckStatus = creditCheckStatus;
    }

    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Long getLoanRequestId() { return loanRequestId; }
    public void setLoanRequestId(Long loanRequestId) { this.loanRequestId = loanRequestId; }
    public Double getDtiRatio() { return dtiRatio; }
    public void setDtiRatio(Double dtiRatio) { this.dtiRatio = dtiRatio; }
    public String getCreditCheckStatus() { return creditCheckStatus; }
    public void setCreditCheckStatus(String creditCheckStatus) { this.creditCheckStatus = creditCheckStatus; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
