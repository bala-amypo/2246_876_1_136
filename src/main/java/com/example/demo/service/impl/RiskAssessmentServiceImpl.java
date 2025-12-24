package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository riskAssessmentRepository;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository riskAssessmentRepository) {
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    @Override
    public List<RiskAssessment> assessRisk(long userId) {
        return riskAssessmentRepository.findByUserId(userId);
    }

    @Override
    public List<RiskAssessment> getLogsByRequest(Long loanRequestId) {
        return riskAssessmentRepository.findByLoanRequestId(loanRequestId);
    }
}