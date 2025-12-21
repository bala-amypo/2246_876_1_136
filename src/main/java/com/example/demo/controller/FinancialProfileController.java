package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;

@RestController
@RequestMapping("/api/financial-profile")
public class FinancialProfileController {

    private final FinancialProfileService service;

    public FinancialProfileController(FinancialProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FinancialProfile> saveProfile(
            @RequestBody FinancialProfile profile) {

        return ResponseEntity.ok(service.saveProfile(profile));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FinancialProfile> getProfile(
            @PathVariable Long userId) {

        return ResponseEntity.ok(service.getProfileByUserId(userId));
    }
}
