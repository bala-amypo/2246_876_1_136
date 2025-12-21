package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.service.RiskAssessmentLogService;

@RestController
@RequestMapping("/api/risk-logs")
public class RiskLogController {

    private final RiskAssessmentLogService riskAssessmentLogService;

    public RiskLogController(RiskAssessmentLogService riskAssessmentLogService) {
        this.riskAssessmentLogService = riskAssessmentLogService;
    }

    @PostMapping("/assess/{loanRequestId}")
    public ResponseEntity<RiskAssessmentLog> assessRisk(
            @PathVariable Long loanRequestId) {

        return ResponseEntity.ok(
                riskAssessmentLogService.assessRisk(loanRequestId)
        );
    }

    @GetMapping("/{loanRequestId}")
    public ResponseEntity<List<RiskAssessmentLog>> getLogs(
            @PathVariable Long loanRequestId) {

        return ResponseEntity.ok(
                riskAssessmentLogService.getByLoanRequestId(loanRequestId)
        );
    }
}
