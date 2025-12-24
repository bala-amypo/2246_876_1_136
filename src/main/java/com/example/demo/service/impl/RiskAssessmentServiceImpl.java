package com.example.demo.service.impl;
import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {
    private final RiskAssessmentRepository riskAssessmentRepository;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository repo) {
        this.riskAssessmentRepository = repo;
    }

    @Override
    public List<RiskAssessment> assessRisk(long userId) {
        return riskAssessmentRepository.findByUserId(userId);
    }
}