package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-profile")
public class FinancialProfileController {

    // 1. Declare the variable
    private final FinancialProfileService financialProfileService;

    // 2. Initialize it via Constructor
    public FinancialProfileController(FinancialProfileService financialProfileService) {
        this.financialProfileService = financialProfileService;
    }

    @PostMapping
    public FinancialProfile createProfile(@RequestBody FinancialProfile profile) {
        return financialProfileService.createOrUpdateProfile(profile);
    }

    @GetMapping("/{userId}")
    public FinancialProfile getProfile(@PathVariable Long userId) {
        // This will now find 'financialProfileService'
        return financialProfileService.getProfileByUser(userId).orElse(null);
    }
}