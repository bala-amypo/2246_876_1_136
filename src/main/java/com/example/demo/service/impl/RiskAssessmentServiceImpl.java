package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {
    private final RiskAssessmentRepository repository;
    private final FinancialProfileRepository financialProfileRepository;

    // The test requires exactly these 2 arguments
    public RiskAssessmentServiceImpl(RiskAssessmentRepository rar, FinancialProfileRepository fpr) {
        this.repository = rar;
        this.financialProfileRepository = fpr;
    }

    @Override 
    public RiskAssessment assessRisk(RiskAssessment log) { 
        // Ensure this returns the saved entity, NOT an ID
        return repository.save(log); 
    }

    @Override public RiskAssessment getLogsByLoanRequestId(Long id) { 
        return repository.findByLoanRequestId(id).orElse(null); 
    }
    @Override public RiskAssessment getByLoanRequestId(Long id) { 
        return getLogsByLoanRequestId(id); 
    }
}