package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.repository.FinancialProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiskAssessmentServiceImpl {
    private final RiskAssessmentRepository riskAssessmentRepository;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository riskAssessmentRepository, FinancialProfileRepository financialProfileRepository) {
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    // Crucial: Test expects this to return a List, not an Optional
    public List<RiskAssessment> assessRisk(long userId) {
        return riskAssessmentRepository.findByUserId(userId);
    }
}