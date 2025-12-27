package com.example.demo.dto;

public class RiskAssessmentResponse {

    private Double riskScore;
    private Double dtiRatio;

    public RiskAssessmentResponse(Double riskScore, Double dtiRatio) {
        this.riskScore = riskScore;
        this.dtiRatio = dtiRatio;
    }

    public Double getRiskScore() { return riskScore; }
    public Double getDtiRatio() { return dtiRatio; }
}
