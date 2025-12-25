package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoanEligibilityServiceImpl {

    private final FinancialProfileService financialProfileService;
    private final RiskAssessmentRepository riskAssessmentRepository;

    // Constructor injection fixes the "cannot find symbol: variable financialProfileService"
    public LoanEligibilityServiceImpl(FinancialProfileService financialProfileService, 
                                      RiskAssessmentRepository riskAssessmentRepository) {
        this.financialProfileService = financialProfileService;
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    public void checkEligibility(LoanRequest loanRequest) {
        // Fixes the "cannot find symbol: variable userId" 
        // We extract the ID from the user associated with the loan request
        if (loanRequest.getUser() == null) {
            throw new RuntimeException("User not associated with loan request");
        }
        Long userId = loanRequest.getUser().getId();

        // Line 33: Fixes "cannot find symbol: variable financialProfileService" 
        // and ensures .orElseThrow() is called on an Optional object
        FinancialProfile profile = financialProfileService.getProfileByUser(userId)
            .orElseThrow(() -> new RuntimeException("Financial Profile not found"));

        // Business Logic Example: Calculate DTI and Save Risk Assessment
        double dtiRatio = (profile.getMonthlyDebt() / profile.getMonthlyIncome()) * 100;
        
        RiskAssessment riskLog = new RiskAssessment();
        riskLog.setUserId(userId);
        riskLog.setLoanRequestId(loanRequest.getId());
        riskLog.setDtiRatio(dtiRatio);
        
        if (dtiRatio < 40) {
            riskLog.setCreditCheckStatus("PASSED");
            riskLog.setRiskScore(800);
            loanRequest.setStatus("APPROVED");
        } else {
            riskLog.setCreditCheckStatus("FAILED");
            riskLog.setRiskScore(400);
            loanRequest.setStatus("REJECTED");
        }

        riskAssessmentRepository.save(riskLog);
    }
}