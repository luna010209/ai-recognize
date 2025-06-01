package com.example.AIRecognize.authentication.user.service;

import com.example.AIRecognize.authentication.user.dto.UserDTO;
import com.example.AIRecognize.authentication.user.entity.UserEntity;
import com.example.AIRecognize.authentication.user.repo.UserRepo;
import com.example.AIRecognize.authentication.verify.entity.Verify;
import com.example.AIRecognize.authentication.verify.repo.VerifyRepo;
import com.example.AIRecognize.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final VerifyRepo verifyRepo;
    private final PasswordEncoder encoder;
    public UserDTO registerUser(UserDTO request){
        if (userRepo.existsByUsername(request.getUsername()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Username does already exist!");
        Verify verify = verifyRepo.findById(request.getEmail()).orElseThrow(
                ()-> new CustomException(HttpStatus.BAD_REQUEST, "This email is not verified!")
        );
        if (!verify.isSuccess())
            throw new CustomException(HttpStatus.BAD_REQUEST, "This email is not verified!");
        
        UserEntity entity = UserEntity.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phone(request.getPhone())
                .avatar(request.getAvatar())
                .build();
        return UserDTO.dto(userRepo.save(entity));
    }
}
