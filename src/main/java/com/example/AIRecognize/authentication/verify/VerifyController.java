package com.example.AIRecognize.authentication.verify;

import com.example.AIRecognize.authentication.verify.dto.VerifyDTO;
import com.example.AIRecognize.authentication.verify.service.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("verify")
public class VerifyController {
    private final VerifyService verifyService;
    @PostMapping("send_mail")
    public VerifyDTO sendMail(@RequestBody VerifyDTO request){
        return verifyService.sendMail(request.getMailPhone());
    }
    @PostMapping("email")
    public VerifyDTO verifyEmail(@RequestBody VerifyDTO request){
        return verifyService.verifyEmail(request);
    }
}
