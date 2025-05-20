package com.reza.userregistrationapp.service;

import com.reza.userregistrationapp.domain.UserAccount;
import com.reza.userregistrationapp.dto.UserRequestDto;
import com.reza.userregistrationapp.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto addUser(UserRequestDto requestDto);

    UserResponseDto getUserByUsername(String username);

}
