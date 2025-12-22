package com.example.demo.service;

import com.example.demo.entity.LoanRequest;

import java.util.List;

public interface LoanRequestService {

    LoanRequest applyLoan(Long userId, LoanRequest request);

    LoanRequest getLoanRequestById(Long loanRequestId);

    List<LoanRequest> getLoansByUserId(Long userId);
}
