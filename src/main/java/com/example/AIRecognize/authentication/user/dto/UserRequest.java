package com.example.AIRecognize.authentication.user.dto;

import com.example.AIRecognize.authentication.user.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String password;
    private String confirmPw;
    private String email;
    private String phone;
    private String avatar;

//    public static UserRequest dto(UserEntity entity){
//        UserRequest userDTO = UserRequest.builder()
//                .username(entity.getUsername())
//                .password(entity.getPassword())
//                .email(entity.getEmail())
//                .phone(entity.getPhone())
//                .avatar(entity.getAvatar())
//                .build();
//        return userDTO;
//    }
}
