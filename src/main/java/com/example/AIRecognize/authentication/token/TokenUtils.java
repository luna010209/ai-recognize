package com.example.AIRecognize.authentication.token;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;

public class TokenUtils {
    private final Key secretKey;
    private final JwtParser jwtParser;

    public TokenUtils(
            @Value("${jwt.secret}")
            String code
    ){
        this.secretKey = Keys.hmacShaKeyFor(code.getBytes());
        this.jwtParser = Jwts.parserBuilder().
                setSigningKey(secretKey).build();
    }
}
