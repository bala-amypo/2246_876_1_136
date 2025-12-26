// package com.example.demo.service.impl;

// import com.example.demo.entity.LoanRequest;
// import com.example.demo.repository.LoanRequestRepository;
// import com.example.demo.repository.UserRepository; // Essential import
// import com.example.demo.service.LoanRequestService;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class LoanRequestServiceImpl implements LoanRequestService {
//     private final LoanRequestRepository repository;
//     private final UserRepository userRepository; 

//     // Test requires 2-arg constructor
//     public LoanRequestServiceImpl(LoanRequestRepository repository, UserRepository userRepository) {
//         this.repository = repository;
//         this.userRepository = userRepository;
//     }

//     @Override public LoanRequest submitRequest(LoanRequest request) { return repository.save(request); }
//     @Override public List<LoanRequest> getRequestsByUser(Long userId) { return repository.findByUserId(userId); }
//     @Override public LoanRequest getById(Long id) { return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found")); }
//     @Override public List<LoanRequest> getAllRequests() { return repository.findAll(); }
// }
package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {
    private final LoanRequestRepository loanRequestRepository;
    private final UserRepository userRepository;
    
    public LoanRequestServiceImpl(LoanRequestRepository loanRequestRepository, UserRepository userRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public LoanRequest submitRequest(LoanRequest request) {
        if (request.getRequestedAmount() == null || request.getRequestedAmount() <= 0) {
            throw new BadRequestException("Invalid requested amount");
        }
        request.setStatus(LoanRequest.Status.PENDING.name());
        return loanRequestRepository.save(request);
    }
    
    @Override
    public List<LoanRequest> getRequestsByUser(Long userId) {
        return loanRequestRepository.findByUserId(userId);
    }
    
    @Override
    public LoanRequest getById(Long id) {
        return loanRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Loan request not found"));
    }
}