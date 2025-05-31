package com.example.AIRecognize.authentication.verify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
public class Verify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mailPhone;
    private Integer verifyCode;
    private Date expiration;
    public Verify(String mailPhone, Integer verifyCode){
        this.mailPhone = mailPhone;
        this.verifyCode = verifyCode;
        this.expiration= Date.from(Instant.now().plusSeconds(60*30));
    }
}
