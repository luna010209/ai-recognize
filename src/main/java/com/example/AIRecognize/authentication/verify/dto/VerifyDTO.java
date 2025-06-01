package com.example.AIRecognize.authentication.verify.dto;

import com.example.AIRecognize.authentication.verify.entity.Verify;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyDTO {
    private String mailPhone;
    private Integer verifyCode;
    private Date expiration;
    private boolean success;
    public static VerifyDTO dto(Verify verify){
        VerifyDTO verifyDTO = VerifyDTO.builder()
                .mailPhone(verify.getMailPhone())
                .verifyCode(verify.getVerifyCode())
                .expiration(verify.getExpiration())
                .success(verify.isSuccess())
                .build();
        return verifyDTO;
    }
}
