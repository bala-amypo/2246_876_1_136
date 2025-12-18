package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.service.LoanRequestService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/loan-requests")
@Tag(name = "Loan Requests")
public class LoanRequestController {
    private final LoanRequestService service;
    private final UserService userService;

    public LoanRequestController(LoanRequestService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<LoanRequest> submit(@RequestBody LoanDtos.LoanRequestDto dto, Authentication auth) {
        Long userId = userService.findByEmail(auth.getName()).getId();
        return ResponseEntity.ok(service.submitLoanRequest(userId, dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanRequest>> getUserRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getRequestsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanRequest> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRequestById(id));
    }
    
    @GetMapping("/")
    public ResponseEntity<List<LoanRequest>> getAll() {
        return ResponseEntity.ok(service.getAllRequests());
    }
}