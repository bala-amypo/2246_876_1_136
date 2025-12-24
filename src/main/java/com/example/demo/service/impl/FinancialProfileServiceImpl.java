package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService;
import org.springframework.stereotype.Service;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository financialProfileRepository;

    public FinancialProfileServiceImpl(FinancialProfileRepository financialProfileRepository, UserRepository userRepository) {
        this.financialProfileRepository = financialProfileRepository;
    }

    @Override
    public FinancialProfile createOrUpdateProfile(FinancialProfile profile) {
        return financialProfileRepository.save(profile);
    }

    @Override
    public FinancialProfile getProfileByUser(Long userId) {
        // Assuming your repository has findByUserId or similar
        return financialProfileRepository.findByUserId(userId);
    }
}