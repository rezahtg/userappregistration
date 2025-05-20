package com.reza.userregistrationapp.service;

import com.reza.userregistrationapp.domain.UserAccount;
import com.reza.userregistrationapp.dto.UserRequestDto;
import com.reza.userregistrationapp.dto.UserResponseDto;
import com.reza.userregistrationapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        UserAccount userAccount = UserAccount.builder()
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .build();
        userAccount = userRepository.save(userAccount);

        return UserResponseDto.builder()
                .username(userAccount.getUsername())
                .email(userAccount.getEmail())
                .build();

    }

    @Override
    public UserResponseDto getUserByUsername(String username) {
        UserAccount userAccount = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserResponseDto.builder()
                .username(userAccount.getUsername())
                .email(userAccount.getEmail())
                .build();
    }
}
