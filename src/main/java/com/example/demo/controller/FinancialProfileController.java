package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.service.FinancialProfileService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-profiles")
@Tag(name = "Financial Profiles")
public class FinancialProfileController {
    private final FinancialProfileService service;
    private final UserService userService;

    public FinancialProfileController(FinancialProfileService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<FinancialProfile> createOrUpdate(@RequestBody LoanDtos.FinancialProfileDto dto, Authentication auth) {
        Long userId = userService.findByEmail(auth.getName()).getId();
        return ResponseEntity.ok(service.createOrUpdateProfile(userId, dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<FinancialProfile> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getProfileByUser(userId));
    }
}