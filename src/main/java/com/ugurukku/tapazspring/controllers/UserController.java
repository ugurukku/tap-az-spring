package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.dto.user.CreateUserRequest;
import com.ugurukku.tapazspring.dto.user.UpdateUserRequest;
import com.ugurukku.tapazspring.dto.user.UserDto;
import com.ugurukku.tapazspring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/count")
    public Long getCount() {
        return service.getCount();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id")
    public ResponseEntity<UserDto> getUser(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody CreateUserRequest userRequest) {
        return ResponseEntity
                .ok(service
                        .addUser(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody UpdateUserRequest updateUserRequest){
        service.updateUser(id,updateUserRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
