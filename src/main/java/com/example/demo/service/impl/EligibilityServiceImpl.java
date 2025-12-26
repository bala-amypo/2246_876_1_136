package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.LoanEligibilityService;
import org.springframework.stereotype.Service;


@Service
public class EligibilityServiceImpl implements LoanEligibilityService {
    private final LoanRequestRepository loanRequestRepository;
    private final UserRepository userRepository; // Added
    private final FinancialProfileRepository financialProfileRepository;
    private final EligibilityResultRepository eligibilityResultRepository;
    private final RiskAssessmentRepository riskAssessmentRepository;

    public EligibilityServiceImpl(LoanRequestRepository loanRequestRepository,
                                  UserRepository userRepository,
                                  FinancialProfileRepository financialProfileRepository,
                                  EligibilityResultRepository eligibilityResultRepository,
                                  RiskAssessmentRepository riskAssessmentRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.userRepository = userRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.eligibilityResultRepository = eligibilityResultRepository;
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    // Update method name
    @Override
    public EligibilityResult getByLoanRequestId(Long loanRequestId) {
        return eligibilityResultRepository.findByLoanRequestId(loanRequestId).orElse(null);
    }
    
    // Ensure evaluateEligibility uses the renamed RiskAssessment class
    @Override
    public EligibilityResult evaluateEligibility(Long loanRequestId) {
        // ... implementation logic ...
        RiskAssessment log = new RiskAssessment(); // Use renamed class
        // ...
        riskAssessmentRepository.save(log);
        return result;
    }
}
