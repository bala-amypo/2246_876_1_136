package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.EligibilityService;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {

    private final EligibilityService service;

    public EligibilityController(EligibilityService service) {
        this.service = service;
    }

    @GetMapping("/{loanRequestId}")
    public ResponseEntity<EligibilityResult> checkEligibility(
            @PathVariable Long loanRequestId) {

        return ResponseEntity.ok(service.checkEligibility(loanRequestId));
    }
}
