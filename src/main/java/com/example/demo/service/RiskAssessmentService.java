package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;
import java.util.List;

public interface RiskAssessmentService {
    List<RiskAssessment> assessRisk(long userId);
    // Add this method called by RiskLogController
    List<RiskAssessment> getLogsByRequest(Long loanRequestId);
}