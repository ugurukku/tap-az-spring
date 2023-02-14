package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.dto.user.CreateUserRequest;
import com.ugurukku.tapazspring.dto.user.UserLoginDto;
import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/register")
    public void addUser(@Valid @RequestBody CreateUserRequest userRequest, HttpServletRequest request) {
        try{service
                .addUser(userRequest, getSiteURL(request));}
        catch (Exception e){
            throw new RuntimeException("Something went wrong!");
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity
                .ok(service
                        .authenticate(userLoginDto));
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

}
