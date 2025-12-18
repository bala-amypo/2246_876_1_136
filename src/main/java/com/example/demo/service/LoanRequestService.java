package com.example.demo.service;

import com.example.demo.entity.LoanRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LoanRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestService {

    private final LoanRequestRepository repository;

    public LoanRequestService(LoanRequestRepository repository) {
        this.repository = repository;
    }

    public LoanRequest submitLoanRequest(LoanRequest request) {
        if (request.getRequestedAmount() == null || request.getRequestedAmount() <= 0) {
            // Must contain "Requested amount"
            throw new BadRequestException("Invalid Amount: Requested amount must be positive");
        }
        request.setStatus("PENDING");
        return repository.save(request);
    }

    public List<LoanRequest> getRequestsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public LoanRequest getRequestById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));
    }

    public List<LoanRequest> getAllRequests() {
        return repository.findAll();
    }
}