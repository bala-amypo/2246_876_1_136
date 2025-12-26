// package com.example.demo.service.impl;

// import com.example.demo.entity.*;
// import com.example.demo.repository.*;
// import com.example.demo.service.LoanEligibilityService;
// import org.springframework.stereotype.Service;

// @Service
// public class EligibilityServiceImpl implements LoanEligibilityService {
//     private final LoanRequestRepository loanRequestRepository;
//     private final FinancialProfileRepository financialProfileRepository;
//     private final UserRepository userRepository;
//     private final EligibilityResultRepository eligibilityResultRepository;
//     private final RiskAssessmentRepository riskAssessmentRepository;

//     // The test requires exactly these 6 arguments in this order
//     public EligibilityServiceImpl(LoanRequestRepository lrr, 
//                                   FinancialProfileRepository fpr, 
//                                   UserRepository ur, 
//                                   EligibilityResultRepository err, 
//                                   RiskAssessmentRepository rar,
//                                   FinancialProfileRepository dummy) { // 6th dummy param
//         this.loanRequestRepository = lrr;
//         this.financialProfileRepository = fpr;
//         this.userRepository = ur;
//         this.eligibilityResultRepository = err;
//         this.riskAssessmentRepository = rar;
//     }

//     @Override public EligibilityResult evaluateEligibility(Long id) {
//         // ... (existing logic)
//         return new EligibilityResult(); // Placeholder
//     }
//     @Override public EligibilityResult getResultByRequest(Long id) { 
//         return eligibilityResultRepository.findByLoanRequestId(id).orElse(null); 
//     }
//     @Override public EligibilityResult getByLoanRequestId(Long id) { 
//         return getResultByRequest(id); 
//     }
// }
package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class EligibilityServiceImpl {
    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final EligibilityResultRepository eligibilityResultRepository;
    
    public EligibilityServiceImpl(LoanRequestRepository loanRequestRepository, 
                                FinancialProfileRepository financialProfileRepository,
                                EligibilityResultRepository eligibilityResultRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.eligibilityResultRepository = eligibilityResultRepository;
    }
    
    public EligibilityResult evaluateEligibility(Long loanRequestId) {
        if (eligibilityResultRepository.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Eligibility already evaluated");
        }
        
        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
            .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));
        
        FinancialProfile profile = financialProfileRepository.findByUserId(loanRequest.getUser().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));
        
        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        
        double netIncome = profile.getMonthlyIncome() - profile.getMonthlyExpenses() - profile.getExistingLoanEmi();
        double maxEligible = netIncome * 60; // 5 years worth
        
        result.setMaxEligibleAmount(Math.max(0, maxEligible));
        result.setRecommendedEmi(netIncome * 0.4);
        result.setIsEligible(maxEligible > 0 && profile.getCreditScore() >= 600);
        
        return eligibilityResultRepository.save(result);
    }
    
    public EligibilityResult getByLoanRequestId(Long loanRequestId) {
        return eligibilityResultRepository.findByLoanRequestId(loanRequestId)
            .orElseThrow(() -> new ResourceNotFoundException("Eligibility result not found"));
    }
}