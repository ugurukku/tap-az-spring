package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.dto.CreateUserRequest;
import com.ugurukku.tapazspring.dto.UserMapper;
import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public User getUser(String id){
        return repository.findById(id).orElse(new User());
    }

    public User addUser(CreateUserRequest userRequest){
        return repository.save(userMapper.toUser(userRequest));
    }

    public Long getCount(){
        return repository.count();
    }

}