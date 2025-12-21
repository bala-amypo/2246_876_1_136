package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.service.RiskAssessmentLogService;

@RestController
@RequestMapping("/api/risk-logs")
public class RiskLogController {

    private final RiskAssessmentLogService service;

    public RiskLogController(RiskAssessmentLogService service) {
        this.service = service;
    }

    @GetMapping("/{loanRequestId}")
    public ResponseEntity<List<RiskAssessmentLog>> getRiskLogs(
            @PathVariable Long loanRequestId) {

        return ResponseEntity.ok(
                service.getLogsByLoanRequestId(loanRequestId));
    }
}
