package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;

public interface FinancialProfileService {
    // Add these methods called by FinancialProfileController
    FinancialProfile createOrUpdateProfile(FinancialProfile profile);
    FinancialProfile getProfileByUser(Long userId);
}