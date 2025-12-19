

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // Required for @WebServlet to work
public class LoanEligibilityEmiRiskCheckerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanEligibilityEmiRiskCheckerApplication.class, args);
    }
}