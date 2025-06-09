package com.example.AIRecognize.authentication.user.service;

import com.example.AIRecognize.authentication.user.dto.UserDTO;
import com.example.AIRecognize.authentication.user.dto.UserLogin;
import com.example.AIRecognize.authentication.user.dto.UserRequest;
import com.example.AIRecognize.authentication.user.entity.UserEntity;
import com.example.AIRecognize.authentication.user.repo.UserRepo;
import com.example.AIRecognize.authentication.verify.entity.Verify;
import com.example.AIRecognize.authentication.verify.repo.VerifyRepo;
import com.example.AIRecognize.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final VerifyRepo verifyRepo;
    private final PasswordEncoder encoder;
    public UserDTO registerUser(UserRequest request){
        if (userRepo.existsByUsername(request.getUsername()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Username does already exist!");
        else if (!request.getPassword().equals(request.getConfirmPw()))
            throw new CustomException(HttpStatus.CONFLICT, "Passwords do not match!");
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username).orElseThrow(
                ()-> new CustomException(HttpStatus.NOT_FOUND, "No exist username")
        );
        return UserLogin.dto(user);
    }
}
