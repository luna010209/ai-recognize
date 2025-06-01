package com.example.AIRecognize.authentication.user.dto;

import com.example.AIRecognize.authentication.user.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;

    public static UserDTO dto(UserEntity entity){
        UserDTO userDTO = UserDTO.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .avatar(entity.getAvatar())
                .build();
        return userDTO;
    }
}
