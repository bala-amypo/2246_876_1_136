package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class RiskAssessment { // Renamed from RiskAssessmentLog
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long loanRequestId;
    private Double dtiRatio;
    private String creditCheckStatus;
    private Instant timestamp = Instant.now();

    public RiskAssessment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getLoanRequestId() { return loanRequestId; }
    public void setLoanRequestId(Long loanRequestId) { this.loanRequestId = loanRequestId; }
    public Double getDtiRatio() { return dtiRatio; }
    public void setDtiRatio(Double dtiRatio) { this.dtiRatio = dtiRatio; }
    public String getCreditCheckStatus() { return creditCheckStatus; }
    public void setCreditCheckStatus(String status) { this.creditCheckStatus = status; }
}