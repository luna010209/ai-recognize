package com.example.AIRecognize.authentication.component;

import com.example.AIRecognize.authentication.user.entity.UserEntity;
import com.example.AIRecognize.authentication.user.repo.UserRepo;
import com.example.AIRecognize.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserComponent {
private final UserRepo userRepo;
    public UserEntity userLogin(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(username);
        UserEntity user = userRepo.findByUsername(username).orElseThrow(
                ()-> new CustomException(HttpStatus.NOT_FOUND, "No exist username!")
        );
        return user;
    }
}
