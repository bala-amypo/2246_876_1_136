// package com.example.demo.service;
// import com.example.demo.entity.RiskAssessment;

// public interface RiskAssessmentService {
//     // Return the entity object to fix "long cannot be converted to RiskAssessment"
//     RiskAssessment assessRisk(RiskAssessment log); 
//     RiskAssessment getLogsByLoanRequestId(Long requestId);
//     RiskAssessment getByLoanRequestId(Long requestId);
// }
package com.example.demo;

public interface RiskAssessmentService {
    RiskAssessment assessRisk(Long loanRequestId);
    RiskAssessment getByLoanRequestId(Long loanRequestId);
}