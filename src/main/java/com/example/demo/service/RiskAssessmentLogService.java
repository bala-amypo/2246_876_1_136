package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.RiskAssessmentLog;

public interface RiskAssessmentLogService {

    RiskAssessmentLog assessRisk(Long loanRequestId);

    List<RiskAssessmentLog> getByLoanRequestId(Long loanRequestId);
}
