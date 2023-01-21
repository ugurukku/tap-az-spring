package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.dto.CreateUserRequest;
import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll(){
        return service.getAll();
    }

    @GetMapping("/:id")
    public User getUser(@RequestParam(name = "id") String id){
        return service.getUser(id);
    }

    @PostMapping
    public User addUser(@RequestBody CreateUserRequest userRequest){
        return service.addUser(userRequest);
    }

    @GetMapping("/count")
    public Long getCount(){
        return service.getCount();
    }

}
