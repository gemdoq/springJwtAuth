package com.example.springjwtauth.service;

import com.example.springjwtauth.domain.dto.UserJoinRequest;
import com.example.springjwtauth.domain.dto.UserLoginRequest;
import com.example.springjwtauth.domain.entity.User;
import com.example.springjwtauth.domain.entity.UserDto;
import com.example.springjwtauth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
        log.info("request pw : {}", userLoginRequest.getPassword());
        log.info("user pw : {}", user.getPassword());
        log.info("isEqual? : {}", userLoginRequest.getPassword().equals(user.getPassword()));

        // deny request if password is invalid
        if(!userLoginRequest.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Password is invalid");
        }

        String token = "Here is your token";
        return token;
    }
}
