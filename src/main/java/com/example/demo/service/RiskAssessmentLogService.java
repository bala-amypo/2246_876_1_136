package com.example.demo.service;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.repository.RiskAssessmentLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskAssessmentLogService {
    private final RiskAssessmentLogRepository repository;

    public RiskAssessmentLogService(RiskAssessmentLogRepository repository) {
        this.repository = repository;
    }

    public void logAssessment(RiskAssessmentLog log) {
        repository.save(log);
    }

    public List<RiskAssessmentLog> getLogsByRequest(Long requestId) {
        return repository.findByLoanRequestId(requestId);
    }
}