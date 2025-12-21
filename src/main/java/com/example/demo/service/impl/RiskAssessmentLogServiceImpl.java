package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RiskAssessmentLogService;

@Service
public class RiskAssessmentLogServiceImpl implements RiskAssessmentLogService {

    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository profileRepository;
    private final RiskAssessmentLogRepository riskRepository;

    public RiskAssessmentLogServiceImpl(LoanRequestRepository loanRequestRepository,
                                     FinancialProfileRepository profileRepository,
                                     RiskAssessmentLogRepository riskRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.profileRepository = profileRepository;
        this.riskRepository = riskRepository;
    }

    @Override
    public RiskAssessmentLog assessRisk(Long loanRequestId) {

        if (!riskRepository.findByLoanRequestId(loanRequestId).isEmpty()) {
            throw new BadRequestException("Risk already assessed");
        }

        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));

        FinancialProfile profile = profileRepository.findByUserId(
                loanRequest.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));

        double obligations =
                profile.getMonthlyExpenses() +
                (profile.getExistingLoanEmi() == null ? 0 : profile.getExistingLoanEmi());

        double dti = obligations / profile.getMonthlyIncome();

        String creditStatus = profile.getCreditScore() >= 650
                ? "APPROVED"
                : "PENDING_REVIEW";

        RiskAssessmentLog log = new RiskAssessmentLog(
                loanRequestId,
                dti,
                creditStatus
        );

        return riskRepository.save(log);
    }

    @Override
    public RiskAssessmentLog getByLoanRequestId(Long loanRequestId) {
        return riskRepository.findByLoanRequestId(loanRequestId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Risk assessment not found"));
    }
}
