package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.User;
import com.example.demo.service.LoanRequestService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/loan-requests")
public class LoanRequestController {

    @Autowired private LoanRequestService service;
    @Autowired private UserService userService;

    @PostMapping("/")
    public LoanRequest submitRequest(@RequestBody LoanDtos.LoanRequestDto dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);

        LoanRequest request = new LoanRequest();
        request.setUser(user);
        request.setRequestedAmount(dto.getRequestedAmount());
        request.setTenureMonths(dto.getTenureMonths());
        request.setPurpose(dto.getPurpose());

        return service.submitLoanRequest(request);
    }

    @GetMapping("/user/{userId}")
    public List<LoanRequest> getUserRequests(@PathVariable Long userId) {
        return service.getRequestsByUser(userId);
    }

    @GetMapping("/{id}")
    public LoanRequest getById(@PathVariable Long id) {
        return service.getRequestById(id);
    }

    @GetMapping("/")
    public List<LoanRequest> listAll() {
        return service.getAllRequests();
    }
}