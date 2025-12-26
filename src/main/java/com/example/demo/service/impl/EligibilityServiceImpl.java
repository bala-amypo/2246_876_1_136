package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.LoanEligibilityService;
import org.springframework.stereotype.Service;


@Service
public class EligibilityServiceImpl implements LoanEligibilityService {
    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;
    private final EligibilityResultRepository eligibilityResultRepository;
    private final RiskAssessmentRepository riskAssessmentRepository;

    // Added 6th dummy repo if test passes it, or keep 5 if log indicates 5 required.
    // Based on Image 2, it seems to pass an extra FinancialProfileRepository at the end.
    public EligibilityServiceImpl(LoanRequestRepository lrr, FinancialProfileRepository fpr, 
                                  UserRepository ur, EligibilityResultRepository err, 
                                  RiskAssessmentRepository rar) {
        this.loanRequestRepository = lrr;
        this.financialProfileRepository = fpr;
        this.userRepository = ur;
        this.eligibilityResultRepository = err;
        this.riskAssessmentRepository = rar;
    }
    // ... implement evaluateEligibility and getResultByRequest using logic from your snippet

    // ... update method implementations to use RiskAssessment renamed class

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {
        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Loan request not found"));

        FinancialProfile profile = financialProfileRepository
                .findByUserId(loanRequest.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Financial profile not found"));

        double dtiRatio = (profile.getExistingLoanEmi() + profile.getMonthlyExpenses()) / profile.getMonthlyIncome();
        boolean eligible = dtiRatio < 0.5 && profile.getCreditScore() >= 650;

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        result.setIsEligible(eligible);
        result.setRiskLevel(eligible ? "LOW" : "HIGH");
        if (!eligible) result.setRejectionReason("High DTI ratio or low credit score");

        eligibilityResultRepository.save(result);

        RiskAssessment log = new RiskAssessment();
        log.setLoanRequestId(loanRequestId);
        log.setDtiRatio(dtiRatio);
        log.setCreditCheckStatus("COMPLETED");
        riskAssessmentRepository.save(log);

        return result;
    }

    @Override
    public EligibilityResult getResultByRequest(Long loanRequestId) {
        return eligibilityResultRepository.findByLoanRequestId(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Eligibility result not found"));
    }
}