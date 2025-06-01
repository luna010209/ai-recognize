package com.example.AIRecognize.authentication.verify.service;

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

@Service
@RequiredArgsConstructor
public class VerifyService {
    private final VerifyRepo verifyRepo;
    private final MailListenerEvent mailListenerEvent;
    @Transactional
    public VerifyDTO sendMail(String email){
        if (verifyRepo.existsById(email))
            verifyRepo.deleteById(email);

        SecureRandom random = new SecureRandom();
        Integer code = 100000 + random.nextInt(900000);

        mailListenerEvent.onApplicationEvent(new MailListener(email, code));

        Verify verify = new Verify(email, code);
        verifyRepo.save(verify);

        return VerifyDTO.dto(verify);
    }
}
