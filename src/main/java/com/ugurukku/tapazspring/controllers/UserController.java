package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> getAll(){
        return repository.findAll();
    }

    @GetMapping("/:id")
    public User getUser(@RequestParam(name = "id") String id){
        return repository.findById(id).get();
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return repository.save(user);
    }

    @GetMapping("/count")
    public Integer getCount(){
        return (int)repository.count();
    }

}
