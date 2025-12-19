package com.example.demo.controller;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.service.RiskAssessmentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk-logs")
@Tag(name = "RiskLog")
public class RiskLogController {

    private final RiskAssessmentService riskAssessmentService;

    public RiskLogController(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }

    @GetMapping("/{loanRequestId}")
    public ResponseEntity<RiskAssessmentLog> getRiskLog(
            @PathVariable Long loanRequestId) {

        return ResponseEntity.ok(
                riskAssessmentService.getByLoanRequestId(loanRequestId)
        );
    }
}
