package com.example.demo.service;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.*;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoanRequestService {
    private final LoanRequestRepository repository;
    private final UserRepository userRepository;

    public LoanRequestService(LoanRequestRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public LoanRequest submitLoanRequest(Long userId, LoanDtos.LoanRequestDto dto) {
        if (dto.getRequestedAmount() <= 0) {
            throw new BadRequestException("Requested amount must be positive"); // Matches requirement keyword
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        LoanRequest req = new LoanRequest();
        req.setUser(user);
        req.setRequestedAmount(dto.getRequestedAmount());
        req.setTenureMonths(dto.getTenureMonths());
        req.setPurpose(dto.getPurpose());
        req.setStatus("PENDING");
        return repository.save(req);
    }

    public List<LoanRequest> getRequestsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public LoanRequest getRequestById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan Request not found"));
    }
    
    public List<LoanRequest> getAllRequests() {
        return repository.findAll();
    }
}