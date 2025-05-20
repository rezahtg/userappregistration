package com.reza.userregistrationapp.controller;

import com.reza.userregistrationapp.domain.UserAccount;
import com.reza.userregistrationapp.dto.UserRequestDto;
import com.reza.userregistrationapp.dto.UserResponseDto;
import com.reza.userregistrationapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.addUser(requestDto));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserResponseDto> getUsersByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

}
