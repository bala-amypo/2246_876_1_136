package com.example.demo.service.impl;
import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.repository.FinancialProfileRepository;
import java.util.List;

public class RiskAssessmentServiceImpl {
    private final RiskAssessmentRepository riskAssessmentRepository;

    // Constructor mismatch fix (Test line 60 expects 2 arguments)
    public RiskAssessmentServiceImpl(RiskAssessmentRepository riskAssessmentRepository, FinancialProfileRepository financialProfileRepository) {
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    // Crucial fix: Return List, not Optional (Matches Mockito errors in Image 8)
    public List<RiskAssessment> assessRisk(long userId) {
        return riskAssessmentRepository.findByUserId(userId);
    }
}