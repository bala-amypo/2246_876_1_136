package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository repository;

    public FinancialProfileServiceImpl(FinancialProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {
        return repository.save(profile);
    }

    @Override
    public FinancialProfile getByUserId(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}
