package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository repository;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logAssessment(RiskAssessmentLog log) {
        repository.save(log);
    }

    @Override
    public List<RiskAssessmentLog> getLogsByRequest(Long requestId) {
        return repository.findByLoanRequestId(requestId);
    }
}
