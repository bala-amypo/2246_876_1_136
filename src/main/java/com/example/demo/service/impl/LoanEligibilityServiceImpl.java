package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.LoanEligibilityService;

@Service
public class LoanEligibilityServiceImpl implements LoanEligibilityService {

    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository profileRepository;
    private final EligibilityResultRepository eligibilityRepository;

    public LoanEligibilityServiceImpl(LoanRequestRepository loanRequestRepository,
                                      FinancialProfileRepository profileRepository,
                                      EligibilityResultRepository eligibilityRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.profileRepository = profileRepository;
        this.eligibilityRepository = eligibilityRepository;
    }

    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {

        if (eligibilityRepository.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Eligibility already evaluated");
        }

        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));

        FinancialProfile profile = profileRepository.findByUserId(
                loanRequest.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));

        double totalObligations =
                profile.getMonthlyExpenses() +
                (profile.getExistingLoanEmi() == null ? 0 : profile.getExistingLoanEmi());

        double dti = totalObligations / profile.getMonthlyIncome();

        boolean eligible = profile.getCreditScore() >= 650 && dti <= 0.5;

        double maxEligibleAmount = eligible ? profile.getMonthlyIncome() * 10 : 0;
        double emi = eligible ? (maxEligibleAmount / loanRequest.getTenureMonths()) : 0;

        String risk = dti < 0.3 ? "LOW" : dti < 0.5 ? "MEDIUM" : "HIGH";

        EligibilityResult result = new EligibilityResult(
                loanRequest,
                eligible,
                maxEligibleAmount,
                emi,
                risk,
                eligible ? null : "High risk profile"
        );

        loanRequest.setStatus(eligible ? "APPROVED" : "REJECTED");
        loanRequestRepository.save(loanRequest);

        return eligibilityRepository.save(result);
    }

    @Override
    public EligibilityResult getByLoanRequestId(Long loanRequestId) {
        return eligibilityRepository.findByLoanRequestId(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Eligibility result not found"));
    }
}
