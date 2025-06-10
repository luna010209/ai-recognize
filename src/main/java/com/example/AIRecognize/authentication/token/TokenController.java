package com.example.AIRecognize.authentication.token;

import com.example.AIRecognize.authentication.token.dto.TokenRequest;
import com.example.AIRecognize.authentication.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/token")
public class TokenController {
    private final TokenService tokenService;
    @PostMapping
    public TokenResponse generateToken(@RequestBody TokenRequest tokenRequest){
        return tokenService.generateToken(tokenRequest);
    }
}
