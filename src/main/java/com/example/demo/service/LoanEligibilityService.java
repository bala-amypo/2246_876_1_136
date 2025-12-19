package com.example.demo.service;

import com.example.demo.entity.LoanEligibility;

public interface LoanEligibilityService {

    LoanEligibility evaluateEligibility(Long loanRequestId);

    LoanEligibility getByLoanRequestId(Long loanRequestId);
}
