package com.example.springjwtauth.domain.dto;

import lombok.Getter;

@Getter
public class UserLoginRequest {
    private String userName;
    private String password;
}
