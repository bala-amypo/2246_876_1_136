package com.example.demo.service;

import com.example.demo.entity.LoanRequest;

public interface EligibilityService {
    boolean checkEligibility(LoanRequest request);
}
