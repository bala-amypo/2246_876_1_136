package com.example.demo.service.impl;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FinancialProfileService; // Import interface
import org.springframework.stereotype.Service;

@Service // This makes it a "Bean"
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;

    public FinancialProfileServiceImpl(FinancialProfileRepository fRepo, UserRepository uRepo) {
        this.financialProfileRepository = fRepo;
        this.userRepository = uRepo;
    }

    @Override
    public FinancialProfile createOrUpdate(FinancialProfile profile) {
        return financialProfileRepository.save(profile);
    }
}