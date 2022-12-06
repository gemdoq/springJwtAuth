package com.example.springjwtauth.service;

import com.example.springjwtauth.domain.dto.UserJoinRequest;
import com.example.springjwtauth.domain.entity.User;
import com.example.springjwtauth.domain.entity.UserDto;
import com.example.springjwtauth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

}
