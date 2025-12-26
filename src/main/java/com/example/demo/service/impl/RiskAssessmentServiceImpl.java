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

    public RiskAssessmentServiceImpl(RiskAssessmentRepository repository, 
                                     FinancialProfileRepository financialProfileRepository) {
        this.repository = repository;
        this.financialProfileRepository = financialProfileRepository;
    }

    @Override
    public void assessRisk(RiskAssessment log) {
        repository.save(log);
    }

    @Override
    public RiskAssessment getLogsByLoanRequestId(Long requestId) {
        // Returns the singular RiskAssessment entity as expected by the Controller/Test
        return repository.findByLoanRequestId(requestId).orElse(null);
    }
}