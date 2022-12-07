package com.example.springjwtauth.controller;

import com.example.springjwtauth.domain.dto.*;
import com.example.springjwtauth.domain.entity.UserDto;
import com.example.springjwtauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> userJoin(@RequestBody UserJoinRequest userJoinRequest) {
        UserDto savedUserDto = userService.addUser(userJoinRequest);
        return Response.success(new UserJoinResponse(savedUserDto.getEmail(), savedUserDto.getUserName()));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.userLogin(userLoginRequest);
        return Response.success(new UserLoginResponse(token));
    }
}