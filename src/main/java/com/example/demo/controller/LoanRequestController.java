package com.example.demo.controller;

import com.example.demo.dto.LoanDtos.LoanRequestDto;
import com.example.demo.entity.LoanRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.LoanRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan-request")
public class LoanRequestController {

    private final LoanRequestRepository loanRepository;
    private final UserRepository userRepository;

    public LoanRequestController(LoanRequestRepository loanRepository,
                                 UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{userId}")
    public LoanRequest applyLoan(@PathVariable Long userId,
                                 @RequestBody LoanRequestDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LoanRequest request = new LoanRequest();
        request.setUser(user);
        request.setRequestedAmount(dto.requestedAmount);
        request.setTenureMonths(dto.tenureMonths);
        request.setPurpose(dto.purpose);

        return loanRepository.save(request);
    }
}
