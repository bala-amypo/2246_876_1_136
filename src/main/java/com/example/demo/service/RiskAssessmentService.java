package com.example.demo.service;
import com.example.demo.entity.RiskAssessment;
public interface RiskAssessmentService {
    void assessRisk(RiskAssessment log);
    RiskAssessment getLogsByLoanRequestId(Long requestId);
    RiskAssessment getByLoanRequestId(Long requestId); // Added for test
}