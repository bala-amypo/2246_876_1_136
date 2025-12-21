package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;

public interface FinancialProfileService {
    FinancialProfile saveProfile(FinancialProfile profile);
    FinancialProfile getProfileByUserId(Long userId);
}
