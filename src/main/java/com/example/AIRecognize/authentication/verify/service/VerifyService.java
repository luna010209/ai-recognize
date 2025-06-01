package com.example.AIRecognize.authentication.verify.service;

import com.example.AIRecognize.authentication.user.repo.UserRepo;
import com.example.AIRecognize.authentication.verify.dto.VerifyDTO;
import com.example.AIRecognize.authentication.verify.entity.Verify;
import com.example.AIRecognize.authentication.verify.repo.VerifyRepo;
import com.example.AIRecognize.authentication.verify.util.MailListener;
import com.example.AIRecognize.authentication.verify.util.MailListenerEvent;
import com.example.AIRecognize.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class VerifyService {
    private final VerifyRepo verifyRepo;
    private final UserRepo userRepo;
    private final MailListenerEvent mailListenerEvent;
    @Transactional
    public VerifyDTO sendMail(String email){
        if (userRepo.existsByEmail(email))
            throw new CustomException(HttpStatus.BAD_REQUEST, "This email is already registered!");

        if (verifyRepo.existsById(email))
            verifyRepo.deleteById(email);

        SecureRandom random = new SecureRandom();
        Integer code = 100000 + random.nextInt(900000);

        mailListenerEvent.onApplicationEvent(new MailListener(email, code));

        Verify verify = new Verify(email, code);
        verifyRepo.save(verify);

        return VerifyDTO.dto(verify);
    }

    public VerifyDTO verifyEmail(VerifyDTO request){
        String email = request.getMailPhone();
        Verify verify = verifyRepo.findById(email).orElseThrow(
                ()-> new CustomException(HttpStatus.BAD_REQUEST, "No exist email!")
        );

        if (verify.getExpiration().before(Date.from(Instant.now())))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Your verified time is expired!");
        else if (!verify.getVerifyCode().equals(request.getVerifyCode()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Your verified code is wrong!");
        verify.setSuccess(true);
        return VerifyDTO.dto(verifyRepo.save(verify));
    }
}
