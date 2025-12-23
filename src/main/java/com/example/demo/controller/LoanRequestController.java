package com.example.demo.controller;

import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-requests")
public class LoanRequestController {

    private final LoanRequestService service;

    public LoanRequestController(LoanRequestService service) {
        this.service = service;
    }

    // POST /api/loan-requests
    @PostMapping
    public LoanRequest submit(@RequestBody LoanRequest request) {
        return service.submitLoanRequest(request);
    }

    // GET /api/loan-requests/user/{userId}
    @GetMapping("/user/{userId}")
    public List<LoanRequest> getByUser(@PathVariable Long userId) {
        return service.getRequestsByUser(userId);
    }

    // GET /api/loan-requests/{id}
    @GetMapping("/{id}")
    public LoanRequest getById(@PathVariable Long id) {
        return service.getRequestById(id);
    }

    // GET /api/loan-requests
    @GetMapping
    public List<LoanRequest> getAll() {
        return service.getAllRequests();
    }
}
