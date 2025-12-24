package com.example.demo.service.impl;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;

public class FinancialProfileServiceImpl {
    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;

    // Constructor mismatch fix (Test line 57 expects 2 arguments)
    public FinancialProfileServiceImpl(FinancialProfileRepository financialProfileRepository, UserRepository userRepository) {
        this.financialProfileRepository = financialProfileRepository;
        this.userRepository = userRepository;
    }

    public FinancialProfile createOrUpdate(FinancialProfile profile) {
        return financialProfileRepository.save(profile);
    }
}