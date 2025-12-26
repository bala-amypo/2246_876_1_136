// package com.example.demo.service;
// import com.example.demo.entity.EligibilityResult;
// public interface LoanEligibilityService {
//     EligibilityResult evaluateEligibility(Long loanRequestId);
//     EligibilityResult getResultByRequest(Long loanRequestId);
//     EligibilityResult getByLoanRequestId(Long loanRequestId); // Added for test
// }
package com.example.demo;

public interface EligibilityService {
    EligibilityResult evaluateEligibility(Long loanRequestId);
    EligibilityResult getByLoanRequestId(Long loanRequestId);
}