package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.serviceImpl.AuthServiceImpl;
import com.example.demo.serviceImpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthServiceImpl authService;
    private final UserServiceImpl userService;

    public AuthController(AuthServiceImpl authService, UserServiceImpl userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.existsByUserName(user.getName())) {
            return new ResponseEntity<>("Error: Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Error: Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        userService.addUser(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }


    @PostMapping(path =  "/login")
    public ResponseEntity<String> login(@RequestBody UserDto UserDto) {

        String token = authService.authenticate(
                UserDto.getName(),
                UserDto.getPassword()
        );

        return ResponseEntity.ok(token);
    }

}
