package com.example.demo.controller;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.service.RiskAssessmentLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/risk-logs")
@Tag(name = "Risk Logs")
public class RiskLogController {
    private final RiskAssessmentLogService service;

    public RiskLogController(RiskAssessmentLogService service) {
        this.service = service;
    }

    @GetMapping("/{loanRequestId}")
    public ResponseEntity<List<RiskAssessmentLog>> getLogs(@PathVariable Long loanRequestId) {
        return ResponseEntity.ok(service.getLogsByRequest(loanRequestId));
    }
}