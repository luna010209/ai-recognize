package com.example.AIRecognize.authentication.verify.repo;

import com.example.AIRecognize.authentication.verify.entity.Verify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerifyRepo extends JpaRepository<Verify, String> {
//    Optional<Verify> findByMailPhone(String mailPhone);
//    boolean existsByMailPhone(String mailPhone);
//    void deleteByMailPhone(String mailPhone);
}
