package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.EligibilityService;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    private final LoanRequestRepository loanRepo;
    private final FinancialProfileRepository profileRepo;
    private final EligibilityResultRepository resultRepo;

    public EligibilityServiceImpl(LoanRequestRepository loanRepo,
                                  FinancialProfileRepository profileRepo,
                                  EligibilityResultRepository resultRepo) {
        this.loanRepo = loanRepo;
        this.profileRepo = profileRepo;
        this.resultRepo = resultRepo;
    }

    @Override
    public EligibilityResult evaluate(Long loanRequestId) {

        LoanRequest loan = loanRepo.findById(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        FinancialProfile profile = profileRepo.findByUserId(loan.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        double disposableIncome = profile.getMonthlyIncome()
                - profile.getMonthlyExpenses()
                - profile.getExistingLoanEmi();

        double maxEligible = Math.max(0, disposableIncome * loan.getTenureMonths());

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loan);
        result.setMaxEligibleAmount(maxEligible);

        return resultRepo.save(result);
    }
}
