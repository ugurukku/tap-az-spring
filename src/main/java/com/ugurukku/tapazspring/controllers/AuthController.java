package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.dto.user.CreateUserRequest;
import com.ugurukku.tapazspring.dto.user.UserLoginDto;
import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin("http://localhost:3000")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/register")
    public void addUser(@Valid @RequestBody CreateUserRequest userRequest) throws MessagingException, UnsupportedEncodingException {
  service.addUser(userRequest);}

    @GetMapping(path = "/verify")
    public Boolean verify(@RequestParam("code") String code){
        return service.verify(code);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity
                .ok(service
                        .authenticate(userLoginDto));
    }


}
