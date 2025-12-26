// package com.example.demo.service;

// import com.example.demo.entity.FinancialProfile;

// public interface FinancialProfileService {
//     FinancialProfile createOrUpdate(FinancialProfile profile);
//     FinancialProfile getByUserId(Long userId);
// }
package com.example.demo;

public interface FinancialProfileService {
    FinancialProfile createOrUpdate(FinancialProfile profile);
    FinancialProfile getByUserId(Long userId);
}