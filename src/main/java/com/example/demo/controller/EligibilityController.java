package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.service.EligibilityService;
import com.example.demo.entity.EligibilityResult;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {

    private final EligibilityService service;

    public EligibilityController(EligibilityService service) {
        this.service = service;
    }

    @PostMapping("/{loanRequestId}")
    public EligibilityResult evaluate(@PathVariable Long loanRequestId) {
        return service.evaluate(loanRequestId);
    }
}
