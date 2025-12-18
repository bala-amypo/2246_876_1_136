package com.example.demo.service;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.repository.RiskAssessmentLogRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiskAssessmentLogService {
    private final RiskAssessmentLogRepository repo;

    public RiskAssessmentLogService(RiskAssessmentLogRepository repo) {
        this.repo = repo;
    }

    public List<RiskAssessmentLog> getLogsByRequest(Long reqId) {
        return repo.findByLoanRequestId(reqId);
    }
}