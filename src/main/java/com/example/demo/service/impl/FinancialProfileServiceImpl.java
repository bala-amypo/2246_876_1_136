package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.FinancialProfileService;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository profileRepo;
    private final UserRepository userRepo;

    public FinancialProfileServiceImpl(FinancialProfileRepository profileRepo, UserRepository userRepo) {
        this.profileRepo = profileRepo;
        this.userRepo = userRepo;
    }

    @Override
    public FinancialProfile createOrUpdate(Long userId, FinancialProfile profile) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        FinancialProfile existing = profileRepo.findByUserId(userId).orElse(null);

        if (existing != null) {
            existing.setMonthlyIncome(profile.getMonthlyIncome());
            existing.setMonthlyExpenses(profile.getMonthlyExpenses());
            existing.setExistingLoanEmi(profile.getExistingLoanEmi());
            existing.setCreditScore(profile.getCreditScore());
            existing.setSavingsBalance(profile.getSavingsBalance());
            return profileRepo.save(existing);
        }

        profile.setUser(user);
        return profileRepo.save(profile);
    }

    @Override
    public FinancialProfile getByUser(Long userId) {
        return profileRepo.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }
}
