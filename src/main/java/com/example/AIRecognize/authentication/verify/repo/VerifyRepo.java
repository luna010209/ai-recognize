package com.example.AIRecognize.authentication.verify.repo;

import com.example.AIRecognize.authentication.verify.entity.Verify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyRepo extends JpaRepository<Verify, Long> {
}
