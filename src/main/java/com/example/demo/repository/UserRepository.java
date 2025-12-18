package com.example.demo.repository;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}


File: src/main/java/com/example/demo/repository/EligibilityResultRepository.java
code
Java
package com.example.demo.repository;
import com.example.demo.entity.EligibilityResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EligibilityResultRepository extends JpaRepository<EligibilityResult, Long> {
    Optional<EligibilityResult> findByLoanRequestId(Long loanRequestId);
}
File: src/main/java/com/example/demo/repository/RiskAssessmentLogRepository.java
code
Java
package com.example.demo.repository;
import com.example.demo.entity.RiskAssessmentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RiskAssessmentLogRepository extends JpaRepository<RiskAssessmentLog, Long> {
    List<RiskAssessmentLog> findByLoanRequestId(Long loanRequestId);
}