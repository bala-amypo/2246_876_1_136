package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;

@RestController
@RequestMapping("/api/loan-requests")
public class LoanRequestController {

    private final LoanRequestService service;

    public LoanRequestController(LoanRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoanRequest> createLoan(
            @RequestBody LoanRequest request) {

        return ResponseEntity.ok(service.createLoanRequest(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanRequest>> getLoans(
            @PathVariable Long userId) {

        return ResponseEntity.ok(service.getLoansByUserId(userId));
    }
}
