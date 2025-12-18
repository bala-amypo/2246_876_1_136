package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityService {
    private final LoanRequestRepository loanRequestRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository resultRepo;
    private final RiskAssessmentLogRepository logRepo;

    public LoanEligibilityService(LoanRequestRepository loanRequestRepo, FinancialProfileRepository profileRepo,
                                  EligibilityResultRepository resultRepo, RiskAssessmentLogRepository logRepo) {
        this.loanRequestRepo = loanRequestRepo;
        this.profileRepo = profileRepo;
        this.resultRepo = resultRepo;
        this.logRepo = logRepo;
    }

    public EligibilityResult evaluateEligibility(Long loanRequestId) {
        LoanRequest req = loanRequestRepo.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan Request not found"));
        FinancialProfile profile = profileRepo.findByUserId(req.getUser().getId())
                .orElseThrow(() -> new BadRequestException("Financial Profile not found"));

        double monthlyDebt = profile.getMonthlyExpenses() + profile.getExistingLoanEmi();
        double dti = (profile.getMonthlyIncome() > 0) ? (monthlyDebt / profile.getMonthlyIncome()) : 1.0;

        String risk = "HIGH";
        if (dti < 0.4 && profile.getCreditScore() > 700) risk = "LOW";
        else if (dti < 0.6 && profile.getCreditScore() > 600) risk = "MEDIUM";

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(req);
        result.setRiskLevel(risk);
        result.setMaxEligibleAmount(profile.getMonthlyIncome() * 10); // Simple logic
        result.setEstimatedEmi(req.getRequestedAmount() / req.getTenureMonths());
        resultRepo.save(result);

        RiskAssessmentLog log = new RiskAssessmentLog();
        log.setLoanRequestId(loanRequestId);
        log.setCalculatedDti(dti);
        log.setCreditCheckStatus(profile.getCreditScore() > 600 ? "PASS" : "FAIL");
        log.setRemarks(risk);
        logRepo.save(log);

        req.setStatus(risk.equals("HIGH") ? "REJECTED" : "APPROVED");
        loanRequestRepo.save(req);

        return result;
    }

    public EligibilityResult getResultByRequest(Long reqId) {
        return resultRepo.findByLoanRequestId(reqId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}