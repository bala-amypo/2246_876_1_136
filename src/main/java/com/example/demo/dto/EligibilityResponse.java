package com.example.demo.dto;

public class EligibilityResponse {

    private Double maxEligibleAmount;

    public EligibilityResponse(Double maxEligibleAmount) {
        this.maxEligibleAmount = maxEligibleAmount;
    }

    public Double getMaxEligibleAmount() {
        return maxEligibleAmount;
    }
}
