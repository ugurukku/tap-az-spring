package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.dto.user.CreateUserRequest;
import com.ugurukku.tapazspring.dto.user.UserDto;
import com.ugurukku.tapazspring.dto.user.UserLoginDto;
import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<User> addUser(@RequestBody CreateUserRequest userRequest) {
        return ResponseEntity
                .ok(service
                        .addUser(userRequest));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity
                .ok(service
                        .authenticate(userLoginDto));
    }

}
