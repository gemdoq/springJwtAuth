package com.example.springjwtauth.service;

import com.example.springjwtauth.domain.dto.UserJoinRequest;
import com.example.springjwtauth.domain.dto.UserLoginRequest;
import com.example.springjwtauth.domain.entity.User;
import com.example.springjwtauth.domain.entity.UserDto;
import com.example.springjwtauth.repository.UserRepository;
import com.example.springjwtauth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;
    private long expiredTimeMs = 1000 * 60 * 60;

    public UserDto addUser(UserJoinRequest userJoinRequest) {
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent((user) -> { throw new RuntimeException();});

        User savedUser = userRepository.save(userJoinRequest.toEntity());
        return UserDto.builder()
                .email(savedUser.getEmail())
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .build();
    }

    public String userLogin(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByUserName(userLoginRequest.getUserName())
                .orElseThrow(() -> { throw new RuntimeException("User is not exist");});

        // deny request if password is invalid
        if(!userLoginRequest.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Password is invalid");
        }

        String token = JwtUtil.generateToken(userLoginRequest.getUserName(), secretKey, expiredTimeMs);
        return token;
    }
}
