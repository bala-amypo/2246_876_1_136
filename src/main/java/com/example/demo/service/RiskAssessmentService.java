package com.example.demo.service;

import com.example.demo.entity.RiskAssessment;
import java.util.List;

public interface RiskAssessmentService {

    // Renamed from logAssessment to assessRisk
    void assessRisk(RiskAssessment log);

    // Renamed from getLogsByRequest to getLogsByLoanRequestId
    RiskAssessment getLogsByLoanRequestId(Long requestId);
}