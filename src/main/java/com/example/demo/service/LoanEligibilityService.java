package com.example.demo.service;
import com.example.demo.entity.EligibilityResult;
public interface LoanEligibilityService {
    EligibilityResult evaluateEligibility(Long loanRequestId);
    EligibilityResult getResultByRequest(Long loanRequestId);
    EligibilityResult getByLoanRequestId(Long loanRequestId); // Added for test
}