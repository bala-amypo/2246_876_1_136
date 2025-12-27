package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.service.RiskAssessmentService;
import com.example.demo.entity.RiskAssessment;

@RestController
@RequestMapping("/api/risk")
public class RiskLogController {

    private final RiskAssessmentService service;

    public RiskLogController(RiskAssessmentService service) {
        this.service = service;
    }

    @PostMapping("/{loanRequestId}")
    public RiskAssessment assess(@PathVariable Long loanRequestId) {
        return service.assess(loanRequestId);
    }
}
