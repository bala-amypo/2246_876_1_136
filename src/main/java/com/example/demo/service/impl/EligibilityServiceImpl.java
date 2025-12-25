package com.example.demo.service.impl;

import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.RiskAssessment;
import com.example.demo.repository.RiskAssessmentRepository;
import com.example.demo.service.EligibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EligibilityServiceImpl implements EligibilityService {

    private final RiskAssessmentRepository riskAssessmentRepository;

    @Override
    public boolean checkEligibility(LoanRequest request) {
        RiskAssessment risk = new RiskAssessment();
        risk.setLoanAmount(request.getAmount());
        risk.setEligible(true);

        riskAssessmentRepository.save(risk);
        return true;
    }
}
