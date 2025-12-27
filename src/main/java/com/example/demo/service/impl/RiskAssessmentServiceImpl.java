package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.RiskAssessmentService;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final RiskAssessmentRepository riskRepo;

    public RiskAssessmentServiceImpl(LoanRequestRepository loanRepo,
                                     FinancialProfileRepository profileRepo,
                                     RiskAssessmentRepository riskRepo) {
        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.riskRepo = riskRepo;
    }

    @Override
    public RiskAssessment assess(Long loanRequestId) {

        LoanRequest loan = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        FinancialProfile profile = profileRepo.findByUserId(loan.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        double dti = profile.getExistingLoanEmi() / profile.getMonthlyIncome();
        double riskScore = (1 - dti) * profile.getCreditScore();

        RiskAssessment risk = new RiskAssessment();
        risk.setLoanRequest(loan);
        risk.setDtiRatio(dti);
        risk.setRiskScore(riskScore);

        return riskRepo.save(risk);
    }
}
