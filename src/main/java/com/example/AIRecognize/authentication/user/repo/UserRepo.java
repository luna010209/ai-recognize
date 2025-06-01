package com.example.AIRecognize.authentication.user.repo;

import com.example.AIRecognize.authentication.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
