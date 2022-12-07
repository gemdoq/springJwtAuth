package com.example.springjwtauth.domain.dto;

import com.example.springjwtauth.domain.entity.User;
import lombok.Getter;

@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;

    public User toEntity(String password) {
        return User.builder()
                .email(this.email)
                .password(password)
                .userName(this.userName)
                .build();
    }
}