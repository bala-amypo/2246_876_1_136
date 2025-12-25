package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;
import java.util.Optional; // Import this

public interface FinancialProfileService {
    FinancialProfile createOrUpdateProfile(FinancialProfile profile);
    
    // Change this to return Optional
    Optional<FinancialProfile> getProfileByUser(Long userId);
}