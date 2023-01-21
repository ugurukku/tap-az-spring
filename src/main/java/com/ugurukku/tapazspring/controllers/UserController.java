package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.dto.user.CreateUserRequest;
import com.ugurukku.tapazspring.dto.user.UpdateUserRequest;
import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/:id")
    public User getUser(@RequestParam(name = "id") String id) {
        return service.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody CreateUserRequest userRequest) {
        return ResponseEntity
                .ok(service
                        .addUser(userRequest));
    }

    @PatchMapping("/image/{id}")
    public ResponseEntity<Void> saveProfilePhoto(@PathVariable("id") String id, MultipartFile file) throws IOException {
        service.saveProfileImage(id, file.getBytes());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody UpdateUserRequest updateUserRequest){
        service.updateUser(id,updateUserRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
