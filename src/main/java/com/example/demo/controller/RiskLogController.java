package com.example.demo.controller;

import com.example.demo.entity.RiskAssessment;
import com.example.demo.service.RiskAssessmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk-logs")
public class RiskLogController {

    private final RiskAssessmentService service;

    public RiskLogController(RiskAssessmentService service) {
        this.service = service;
    }

    @GetMapping("/{loanRequestId}")
    public RiskAssessment getLogs(@PathVariable Long loanRequestId) {
        // Calling the method name expected by the test
        return service.getLogsByLoanRequestId(loanRequestId);
    }
}