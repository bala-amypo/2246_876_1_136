package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository financialProfileRepository;

    public FinancialProfileServiceImpl(FinancialProfileRepository financialProfileRepository) {
        this.financialProfileRepository = financialProfileRepository;
    }

    @Override
    public FinancialProfile createOrUpdateProfile(FinancialProfile profile) {
        return financialProfileRepository.save(profile);
    }

    @Override
    public Optional<FinancialProfile> getProfileByUser(Long userId) {
        // This now correctly returns an Optional to the caller
        return financialProfileRepository.findByUserId(userId);
    }
}