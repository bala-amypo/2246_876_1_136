package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.FinancialProfileRepository; // Added this import
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository repository;
    private final FinancialProfileRepository financialProfileRepository; // Added field

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
        return repository.findByLoanRequestId(requestId).orElse(null);
    }
}