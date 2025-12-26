package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.LoanEligibilityService;
import org.springframework.stereotype.Service;

@Service
public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final EligibilityResultRepository eligibilityResultRepository;
    private final RiskAssessmentLogRepository riskAssessmentLogRepository;

    public LoanEligibilityServiceImpl(
            LoanRequestRepository loanRequestRepository,
            FinancialProfileRepository financialProfileRepository,
            EligibilityResultRepository eligibilityResultRepository,
            RiskAssessmentLogRepository riskAssessmentLogRepository) {

        this.loanRequestRepository = loanRequestRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.eligibilityResultRepository = eligibilityResultRepository;
        this.riskAssessmentLogRepository = riskAssessmentLogRepository;
    }

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {

        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Loan request not found"));

        FinancialProfile profile = financialProfileRepository
                .findByUserId(loanRequest.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Financial profile not found"));

        double dtiRatio = (profile.getExistingLoanEmi()
                + profile.getMonthlyExpenses()) / profile.getMonthlyIncome();

        boolean eligible = dtiRatio < 0.5 && profile.getCreditScore() >= 650;

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        result.setIsEligible(eligible);
        result.setRiskLevel(eligible ? "LOW" : "HIGH");

        if (!eligible) {
            result.setRejectionReason("High DTI ratio or low credit score");
        }

        eligibilityResultRepository.save(result);

        RiskAssessmentLog log = new RiskAssessmentLog();
        log.setLoanRequestId(loanRequestId);
        log.setDtiRatio(dtiRatio);
        log.setCreditCheckStatus("COMPLETED");
        riskAssessmentLogRepository.save(log);

        return result;
    }

    @Override
    public EligibilityResult getResultByRequest(Long loanRequestId) {
        return eligibilityResultRepository.findByLoanRequestId(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Eligibility result not found"));
    }
}
