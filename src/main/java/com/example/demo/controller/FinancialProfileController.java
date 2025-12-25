package com.example.demo.controller;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-profiles")
public class FinancialProfileController {

    private final FinancialProfileService service;

    public FinancialProfileController(FinancialProfileService service) {
        this.service = service;
    }

    @PostMapping
    public FinancialProfile createOrUpdate(@RequestBody FinancialProfile profile) {
        return service.createOrUpdateProfile(profile);
    }
    @GetMapping("/{userId}")
     public FinancialProfile getProfile(@PathVariable Long userId) {
    // Add .orElse(null) or .orElseThrow() here as well
         return financialProfileService.getProfileByUser(userId).orElse(null);
    }
    
}
