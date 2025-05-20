package com.reza.userregistrationapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reza.userregistrationapp.dto.UserRequestDto;
import com.reza.userregistrationapp.dto.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserResponseDto userResponseDto;
    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
        userRequestDto = UserRequestDto.builder()
                .username("rezaHutagaol")
                .email("rezaHutagaol@gmail.com")
                .password("rezaHutagaol")
                .build();

        userResponseDto = UserResponseDto.builder()
                .username("rezaHutagaol")
                .email("rezaHutagaol@gmail.com")
                .build();
    }

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("rezaHutagaol"))
                .andExpect(jsonPath("$.email").value("rezaHutagaol@gmail.com"));
    }

    @Test
    void getUsersByUsername() throws Exception {

        // Register user first
        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isOk());

        // Then fetch user
        mockMvc.perform(get("/api/v1/user/rezaHutagaol"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("rezaHutagaol"))
                .andExpect(jsonPath("$.email").value("rezaHutagaol@gmail.com"));

    }
}