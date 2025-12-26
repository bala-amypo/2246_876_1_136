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

    // Test suite requires this specific constructor signature
    public RiskAssessmentServiceImpl(RiskAssessmentRepository repository, 
                                     FinancialProfileRepository financialProfileRepository,
                                     RiskAssessmentRepository dummy) {
        this.repository = repository;
        this.financialProfileRepository = financialProfileRepository;
    }

    @Override
    public void assessRisk(RiskAssessment log) {
        repository.save(log);
    }

    @Override
    public RiskAssessment getLogsByLoanRequestId(Long requestId) {
        return repository.findByLoanRequestId(requestId).orElse(null);
    }

    // THIS METHOD WAS MISSING
    @Override
    public RiskAssessment getByLoanRequestId(Long requestId) {
        return getLogsByLoanRequestId(requestId);
    }
}