package com.example.demo.service;

import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.LoanRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository repository;

    public LoanRequestServiceImpl(LoanRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoanRequest apply(LoanRequest request) {
        return repository.save(request);
    }
}
