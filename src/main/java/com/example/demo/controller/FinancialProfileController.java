package com.example.demo.controller;

import com.example.demo.dto.LoanDtos.FinancialProfileDto;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-profiles")
@Tag(name = "FinancialProfile")
public class FinancialProfileController {

    private final FinancialProfileService financialProfileService;

    public FinancialProfileController(FinancialProfileService financialProfileService) {
        this.financialProfileService = financialProfileService;
    }

    @PostMapping
    public ResponseEntity<FinancialProfile> createOrUpdate(
            @RequestBody FinancialProfile profile) {

        return ResponseEntity.ok(financialProfileService.createOrUpdate(profile));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<FinancialProfile> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(financialProfileService.getByUserId(userId));
    }
}
