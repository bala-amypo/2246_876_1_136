package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.service.FinancialProfileService;
import com.example.demo.entity.FinancialProfile;

@RestController
@RequestMapping("/api/profile")
public class FinancialProfileController {

    private final FinancialProfileService service;

    public FinancialProfileController(FinancialProfileService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public FinancialProfile save(@PathVariable Long userId,
                                 @RequestBody FinancialProfile profile) {
        return service.createOrUpdate(userId, profile);
    }

    @GetMapping("/{userId}")
    public FinancialProfile get(@PathVariable Long userId) {
        return service.getByUser(userId);
    }
}
