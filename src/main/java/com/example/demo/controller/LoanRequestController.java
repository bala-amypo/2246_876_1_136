package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.service.LoanRequestService;
import com.example.demo.entity.LoanRequest;

@RestController
@RequestMapping("/api/loan")
public class LoanRequestController {

    private final LoanRequestService service;

    public LoanRequestController(LoanRequestService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public LoanRequest submit(@PathVariable Long userId,
                              @RequestBody LoanRequest request) {
        return service.submit(userId, request);
    }

    @GetMapping("/{userId}")
    public List<LoanRequest> getUserLoans(@PathVariable Long userId) {
        return service.getByUser(userId);
    }
}
