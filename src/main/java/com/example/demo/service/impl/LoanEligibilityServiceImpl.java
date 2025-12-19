package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    @Override
    public boolean checkEligibility(Long userId) {
        return true; // dummy logic
    }
}
