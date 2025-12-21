package com.example.demo.service.impl;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.service.RiskAssessmentLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskAssessmentLogServiceImpl implements RiskAssessmentLogService {

    @Override
    public RiskAssessmentLog assessRisk(Long loanRequestId) {
        RiskAssessmentLog log = new RiskAssessmentLog();
        log.setRiskLevel("LOW");
        return log;
    }

    @Override
    public List<RiskAssessmentLog> getByLoanRequestId(Long loanRequestId) {
        return List.of();
    }
}
