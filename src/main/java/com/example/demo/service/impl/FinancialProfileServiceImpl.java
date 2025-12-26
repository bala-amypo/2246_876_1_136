// package com.example.demo.service.impl;

// import com.example.demo.entity.FinancialProfile;
// import com.example.demo.repository.FinancialProfileRepository;
// import com.example.demo.repository.UserRepository; // Added import
// import com.example.demo.service.FinancialProfileService;
// import org.springframework.stereotype.Service;

// @Service
// public class FinancialProfileServiceImpl implements FinancialProfileService {

//     private final FinancialProfileRepository repository;
//     private final UserRepository userRepository;

//     public FinancialProfileServiceImpl(FinancialProfileRepository repository, UserRepository userRepository) {
//         this.repository = repository;
//         this.userRepository = userRepository;
//     }

//     @Override
//     public FinancialProfile createOrUpdate(FinancialProfile profile) {
//         return repository.save(profile);
//     }

//     @Override
//     public FinancialProfile getByUserId(Long userId) {
//         return repository.findByUserId(userId)
//                 .orElseThrow(() -> new RuntimeException("Profile not found"));
//     }
// }
package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FinancialProfileServiceImpl {
    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;
    
    public FinancialProfileServiceImpl(FinancialProfileRepository financialProfileRepository, UserRepository userRepository) {
        this.financialProfileRepository = financialProfileRepository;
        this.userRepository = userRepository;
    }
    
    public FinancialProfile createOrUpdate(FinancialProfile profile) {
        if (profile.getCreditScore() != null && profile.getCreditScore() < 300) {
            throw new BadRequestException("Invalid credit score");
        }
        
        Optional<FinancialProfile> existing = financialProfileRepository.findByUserId(profile.getUser().getId());
        if (existing.isPresent()) {
            FinancialProfile existingProfile = existing.get();
            existingProfile.setMonthlyIncome(profile.getMonthlyIncome());
            existingProfile.setMonthlyExpenses(profile.getMonthlyExpenses());
            existingProfile.setExistingLoanEmi(profile.getExistingLoanEmi());
            existingProfile.setCreditScore(profile.getCreditScore());
            existingProfile.setSavingsBalance(profile.getSavingsBalance());
            return financialProfileRepository.save(existingProfile);
        }
        
        return financialProfileRepository.save(profile);
    }
    
    public FinancialProfile getByUserId(Long userId) {
        return financialProfileRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found"));
    }
}