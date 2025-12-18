package com.example.demo.service;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.LoanRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityService {

    private final EligibilityResultRepository eligibilityRepository;
    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository profileRepository;
    private final RiskAssessmentLogService riskLogService;

    public LoanEligibilityService(EligibilityResultRepository eligibilityRepository,
                                  LoanRequestRepository loanRequestRepository,
                                  FinancialProfileRepository profileRepository,
                                  RiskAssessmentLogService riskLogService) {
        this.eligibilityRepository = eligibilityRepository;
        this.loanRequestRepository = loanRequestRepository;
        this.profileRepository = profileRepository;
        this.riskLogService = riskLogService;
    }

    public EligibilityResult evaluateEligibility(Long loanRequestId) {
        LoanRequest request = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));

        FinancialProfile profile = profileRepository.findByUserId(request.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));

        // Logic
        double monthlyIncome = profile.getMonthlyIncome();
        double totalObligations = profile.getExistingLoanEmi() + profile.getMonthlyExpenses();
        // Assuming simple DTI calculation as per requirement (Obligations / Income)
        double dtiRatio = (monthlyIncome > 0) ? totalObligations / monthlyIncome : 1.0;

        boolean isEligible = true;
        String riskLevel = "LOW";
        String reason = null;

        if (profile.getCreditScore() < 300) {
            isEligible = false;
            riskLevel = "HIGH";
            reason = "Credit Score too low";
        } else if (dtiRatio > 0.6) {
             // Higher risk
             riskLevel = "HIGH";
             if(dtiRatio > 0.8) {
                 isEligible = false;
                 reason = "DTI too high";
             }
        } else if (dtiRatio > 0.4) {
            riskLevel = "MEDIUM";
        }

        double maxEligible = monthlyIncome * 12; // Simple multiplier

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(request);
        result.setEligible(isEligible);
        result.setMaxEligibleAmount(maxEligible);
        result.setEstimatedEmi(request.getRequestedAmount() * 0.1); // Placeholder 10%
        result.setRiskLevel(riskLevel);
        result.setRejectionReason(reason);

        eligibilityRepository.save(result);

        // Log
        RiskAssessmentLog log = new RiskAssessmentLog();
        log.setLoanRequestId(loanRequestId);
        log.setDtiRatio(dtiRatio);
        log.setCreditCheckStatus(profile.getCreditScore() > 650 ? "PASS" : "WARN");
        riskLogService.logAssessment(log);

        return result;
    }

    public EligibilityResult getResultByRequest(Long requestId) {
        return eligibilityRepository.findByLoanRequestId(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}