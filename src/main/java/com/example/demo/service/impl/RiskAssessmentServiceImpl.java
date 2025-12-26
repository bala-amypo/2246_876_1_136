// package com.example.demo.service.impl;

// import com.example.demo.entity.RiskAssessment;
// import com.example.demo.repository.FinancialProfileRepository;
// import com.example.demo.repository.RiskAssessmentRepository;
// import com.example.demo.service.RiskAssessmentService;
// import org.springframework.stereotype.Service;

// @Service
// public class RiskAssessmentServiceImpl implements RiskAssessmentService {
//     private final RiskAssessmentRepository repository;
//     private final FinancialProfileRepository financialProfileRepository;

//     // The test requires exactly these 2 arguments
//     public RiskAssessmentServiceImpl(RiskAssessmentRepository rar, FinancialProfileRepository fpr) {
//         this.repository = rar;
//         this.financialProfileRepository = fpr;
//     }

//     @Override 
//     public RiskAssessment assessRisk(RiskAssessment log) { 
//         // Ensure this returns the saved entity, NOT an ID
//         return repository.save(log); 
//     }

//     @Override public RiskAssessment getLogsByLoanRequestId(Long id) { 
//         return repository.findByLoanRequestId(id).orElse(null); 
//     }
//     @Override public RiskAssessment getByLoanRequestId(Long id) { 
//         return getLogsByLoanRequestId(id); 
//     }
// }
package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentServiceImpl {
    private final LoanRequestRepository loanRequestRepository;
    private final FinancialProfileRepository financialProfileRepository;
    private final RiskAssessmentRepository riskAssessmentRepository;
    
    public RiskAssessmentServiceImpl(LoanRequestRepository loanRequestRepository,
                                   FinancialProfileRepository financialProfileRepository,
                                   RiskAssessmentRepository riskAssessmentRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.financialProfileRepository = financialProfileRepository;
        this.riskAssessmentRepository = riskAssessmentRepository;
    }
    
    public RiskAssessment assessRisk(Long loanRequestId) {
        if (riskAssessmentRepository.findByLoanRequestId(loanRequestId).isPresent()) {
            throw new BadRequestException("Risk already assessed");
        }
        
        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
            .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));
        
        FinancialProfile profile = financialProfileRepository.findByUserId(loanRequest.getUser().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));
        
        RiskAssessment assessment = new RiskAssessment();
        assessment.setLoanRequest(loanRequest);
        
        double dtiRatio = profile.getMonthlyIncome() > 0 ? 
            (profile.getMonthlyExpenses() + profile.getExistingLoanEmi()) / profile.getMonthlyIncome() : 0.0;
        
        assessment.setDtiRatio(dtiRatio);
        
        double riskScore = calculateRiskScore(profile, dtiRatio);
        assessment.setRiskScore(riskScore);
        
        if (riskScore < 30) assessment.setRiskCategory("LOW");
        else if (riskScore < 70) assessment.setRiskCategory("MEDIUM");
        else assessment.setRiskCategory("HIGH");
        
        return riskAssessmentRepository.save(assessment);
    }
    
    private double calculateRiskScore(FinancialProfile profile, double dtiRatio) {
        double score = 0;
        
        if (profile.getCreditScore() < 600) score += 40;
        else if (profile.getCreditScore() < 700) score += 20;
        
        if (dtiRatio > 0.5) score += 30;
        else if (dtiRatio > 0.3) score += 15;
        
        if (profile.getSavingsBalance() < 10000) score += 20;
        
        return Math.min(100, score);
    }
    
    public RiskAssessment getByLoanRequestId(Long loanRequestId) {
        return riskAssessmentRepository.findByLoanRequestId(loanRequestId)
            .orElseThrow(() -> new ResourceNotFoundException("Risk assessment not found"));
    }
}