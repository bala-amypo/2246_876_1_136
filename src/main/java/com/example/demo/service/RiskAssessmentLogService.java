package com.example.demo.service;

import com.example.demo.entity.RiskAssessmentLog;

import java.util.List;

public interface RiskAssessmentLogService {

    RiskAssessmentLog saveLog(RiskAssessmentLog log);

    List<RiskAssessmentLog> getLogsByLoanRequestId(Long loanRequestId);
}
