package com.example.demo.service;

import com.example.demo.entity.LoanRequest;
import java.util.List;

public interface LoanRequestService {
    LoanRequest submit(Long userId, LoanRequest request);
    List<LoanRequest> getByUser(Long userId);
}
