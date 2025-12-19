package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    @Override
    public String assessRisk(Long loanId) {
        return "LOW";
    }
}
