package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
public class RiskAssessmentLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loanRequestId;
    private Double calculatedDti;
    private String creditCheckStatus;
    private String remarks;

    @CreationTimestamp
    private Timestamp logTimestamp;

    // Constructors
    public RiskAssessmentLog() {}

    public RiskAssessmentLog(Long id, Long loanRequestId, Double calculatedDti, String creditCheckStatus, String remarks, Timestamp logTimestamp) {
        this.id = id;
        this.loanRequestId = loanRequestId;
        this.calculatedDti = calculatedDti;
        this.creditCheckStatus = creditCheckStatus;
        this.remarks = remarks;
        this.logTimestamp = logTimestamp;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLoanRequestId() { return loanRequestId; }
    public void setLoanRequestId(Long loanRequestId) { this.loanRequestId = loanRequestId; }

    public Double getCalculatedDti() { return calculatedDti; }
    public void setCalculatedDti(Double calculatedDti) { this.calculatedDti = calculatedDti; }

    public String getCreditCheckStatus() { return creditCheckStatus; }
    public void setCreditCheckStatus(String creditCheckStatus) { this.creditCheckStatus = creditCheckStatus; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public Timestamp getLogTimestamp() { return logTimestamp; }
    public void setLogTimestamp(Timestamp logTimestamp) { this.logTimestamp = logTimestamp; }
}