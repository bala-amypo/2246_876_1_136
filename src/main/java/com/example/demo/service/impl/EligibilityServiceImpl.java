package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.LoanEligibilityService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl {

    private final LoanEligibilityService loanEligibilityService;

    public EligibilityServiceImpl(LoanEligibilityService loanEligibilityService) {
        this.loanEligibilityService = loanEligibilityService;
    }

    /**
     * Called by tests and controllers
     */
    public EligibilityResult evaluateEligibility(Long loanRequestId) {
        return loanEligibilityService.evaluateEligibility(loanRequestId);
    }

    /**
     * Called by tests
     */
    public EligibilityResult getResultByRequest(Long loanRequestId) {
        return loanEligibilityService.getResultByRequest(loanRequestId);
    }
}
