package com.example.AIRecognize.authentication.user.dto;

import com.example.AIRecognize.authentication.user.entity.UserEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogin implements UserDetails {
    private String username;
    private String password;
    private String stringAuthorities;

    public static UserLogin dto(UserEntity entity){
        UserLogin userLogin = UserLogin.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .stringAuthorities(entity.getAuthorities())
                .build();
        return userLogin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] listAuthorities = stringAuthorities.split(",");
        return Arrays.stream(listAuthorities).map(SimpleGrantedAuthority::new).toList();
    }
}
