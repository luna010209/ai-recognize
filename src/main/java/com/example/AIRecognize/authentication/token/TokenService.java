package com.example.AIRecognize.authentication.token;

import com.example.AIRecognize.authentication.token.dto.TokenRequest;
import com.example.AIRecognize.authentication.token.dto.TokenResponse;
import com.example.AIRecognize.authentication.user.dto.UserLogin;
import com.example.AIRecognize.authentication.user.service.UserService;
import com.example.AIRecognize.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenUtils tokenUtils;
    private final UserService userService;
    private final PasswordEncoder encoder;
    public TokenResponse generateToken(TokenRequest tokenRequest){
        UserLogin user = (UserLogin) userService.loadUserByUsername(tokenRequest.getUsername());
        if (!encoder.matches(tokenRequest.getPassword(), user.getPassword())){
            throw new CustomException(HttpStatus.CONFLICT, "Wrong password");
        }
        return new TokenResponse(tokenUtils.generateToken(tokenRequest.getUsername()));
    }
}
