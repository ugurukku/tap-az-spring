package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.dto.user.CreateUserRequest;
import com.ugurukku.tapazspring.dto.user.UserDto;
import com.ugurukku.tapazspring.dto.user.UserLoginDto;
import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserDto> addUser(@RequestBody CreateUserRequest userRequest) {
        return ResponseEntity
                .ok(service
                        .addUser(userRequest));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity
                .ok(service
                        .auth(userLoginDto));
    }

}
