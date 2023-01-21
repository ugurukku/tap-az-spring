package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.dto.user.UpdateUserRequest;
import com.ugurukku.tapazspring.dto.user.CreateUserRequest;
import com.ugurukku.tapazspring.dto.user.UserMapper;
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

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getUserById(String id) {
        return repository.findById(id).orElse(new User());
    }

    public Long getCount() {
        return repository.count();
    }

    public User addUser(CreateUserRequest userRequest) {
        return repository.save(userMapper.toUser(userRequest));
    }

    public void saveProfileImage(String id, byte[] image){
        User user = getUserById(id);
        user.setImage(image);
    }

    public void updateUser(String id, UpdateUserRequest userRequest) {
        User user = getUserById(id);
        user.setUsername(userRequest.username());
        user.setPassword(userRequest.password() != null ? userRequest.password() : user.getPassword());

        repository.save(user);
    }

}
