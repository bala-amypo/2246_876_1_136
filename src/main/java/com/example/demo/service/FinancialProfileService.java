package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;

public interface FinancialProfileService {

    FinancialProfile getByUserId(Long userId);

    FinancialProfile saveProfile(Long userId, FinancialProfile profile);
}
