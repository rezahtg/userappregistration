package com.reza.userregistrationapp.service;

import com.reza.userregistrationapp.domain.UserAccount;
import com.reza.userregistrationapp.dto.UserResponseDto;
import com.reza.userregistrationapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserByUsername() {
        //given
        UserAccount userAccount = UserAccount.builder()
                .id(1L)
                .username("username")
                .email("email")
                .password("password")
                .build();

        //when
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(userAccount));

        //Then
        UserResponseDto result = userService.getUserByUsername("username");

        assertNotNull(result);
        assertEquals("username", result.getUsername());
        assertEquals("email", result.getEmail());

    }

    @Test
    void addUser() {
        // given
        UserAccount userAccount = UserAccount.builder()
                .id(1L)
                .username("user1")
                .email("email1")
                .password("password")
                .build();

        // when
        when(userRepository.save(any())).thenReturn(userAccount);

        UserAccount result = userRepository.save(any());

        assertNotNull(result);
        assertEquals("user1", result.getUsername());
        assertEquals("email1", result.getEmail());

    }
}