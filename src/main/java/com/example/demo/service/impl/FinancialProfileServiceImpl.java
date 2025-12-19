package com.example.demo.service;

import com.example.demo.entity.FinancialProfile;
import com.example.demo.repository.FinancialProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class FinancialProfileServiceImpl implements FinancialProfileService {

    private final FinancialProfileRepository repository;

    public FinancialProfileServiceImpl(FinancialProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public FinancialProfile saveProfile(FinancialProfile profile) {
        return repository.save(profile);
    }
}
