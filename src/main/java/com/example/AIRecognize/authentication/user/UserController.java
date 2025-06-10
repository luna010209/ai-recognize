package com.example.AIRecognize.authentication.user;

import com.example.AIRecognize.authentication.user.dto.UserDTO;
import com.example.AIRecognize.authentication.user.dto.UserRequest;
import com.example.AIRecognize.authentication.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    @PostMapping("register")
    public UserDTO registerUser(@RequestBody UserRequest request){
        return userService.registerUser(request);
    }

    @GetMapping("login")
    public UserDTO loginUser(){
        return userService.loginUser();
    }
}
