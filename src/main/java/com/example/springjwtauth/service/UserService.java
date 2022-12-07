package com.example.springjwtauth.service;

import com.example.springjwtauth.domain.dto.UserJoinRequest;
import com.example.springjwtauth.domain.dto.UserLoginRequest;
import com.example.springjwtauth.domain.entity.User;
import com.example.springjwtauth.domain.entity.UserDto;
import com.example.springjwtauth.exception.ErrorCode;
import com.example.springjwtauth.exception.UserException;
import com.example.springjwtauth.repository.UserRepository;
import com.example.springjwtauth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${jwt.secret}")
    private String secretKey;
    private long expiredTimeMs = 1000 * 60 * 60;

    public UserDto addUser(UserJoinRequest userJoinRequest) {
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent((user) -> { throw new UserException(ErrorCode.DUPLICATED_USER_NAME, String.format("UserName:%s", userJoinRequest.getUserName()));
                });

        User savedUser = userRepository.save(userJoinRequest.toEntity(bCryptPasswordEncoder.encode(userJoinRequest.getPassword())));
        return UserDto.builder()
                .email(savedUser.getEmail())
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .build();
    }

    public String userLogin(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByUserName(userLoginRequest.getUserName())
                .orElseThrow(() -> new UserException(ErrorCode.NOT_FOUND, String.format("UserName %s is not registered user", userLoginRequest.getUserName())));

        // deny request if password is invalid
        if(!bCryptPasswordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new UserException(ErrorCode.INVALID_PASSWORD, "Incorrect password");
        }

        String token = JwtUtil.generateToken(userLoginRequest.getUserName(), secretKey, expiredTimeMs);
        return token;
    }
}