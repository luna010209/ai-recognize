package com.example.AIRecognize.authentication.verify.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class MailListener extends ApplicationEvent {
    private String email;
    private Integer code;
    public MailListener(String email, Integer code) {
        super(email);
        this.email = email;
        this.code= code;
    }
}
