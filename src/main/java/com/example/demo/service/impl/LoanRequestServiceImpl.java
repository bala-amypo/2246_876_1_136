package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.service.LoanRequestService;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository loanRepo;
    private final UserRepository userRepo;

    public LoanRequestServiceImpl(LoanRequestRepository loanRepo, UserRepository userRepo) {
        this.loanRepo = loanRepo;
        this.userRepo = userRepo;
    }

    @Override
    public LoanRequest submit(Long userId, LoanRequest request) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        request.setUser(user);
        return loanRepo.save(request);
    }

    @Override
    public List<LoanRequest> getByUser(Long userId) {
        return loanRepo.findByUserId(userId);
    }
}
