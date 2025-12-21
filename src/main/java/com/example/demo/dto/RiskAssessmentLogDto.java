package com.example.demo.dto;

import java.time.LocalDateTime;

public class RiskAssessmentLogDto {

    private Long id;
    private Long loanRequestId;
    private Double dtiRatio;
    private String creditCheckStatus;
    private LocalDateTime timestamp;

    public RiskAssessmentLogDto() {
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLoanRequestId() { return loanRequestId; }
    public void setLoanRequestId(Long loanRequestId) { this.loanRequestId = loanRequestId; }

    public Double getDtiRatio() { return dtiRatio; }
    public void setDtiRatio(Double dtiRatio) { this.dtiRatio = dtiRatio; }

    public String getCreditCheckStatus() { return creditCheckStatus; }
    public void setCreditCheckStatus(String creditCheckStatus) { this.creditCheckStatus = creditCheckStatus; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
