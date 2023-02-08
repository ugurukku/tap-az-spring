package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.dto.user.UpdateUserRequest;
import com.ugurukku.tapazspring.dto.user.CreateUserRequest;
import com.ugurukku.tapazspring.dto.user.UserDto;
import com.ugurukku.tapazspring.dto.user.UserMapper;
import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.exceptions.user.UserAlreadyExistsException;
import com.ugurukku.tapazspring.exceptions.user.UserNotFoundException;
import com.ugurukku.tapazspring.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public List<UserDto> getAll() {
        return userMapper.toUserDtoList(repository.findAll());
    }

    public User getUserByEmail(String email) {
        return repository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found for email : " + email));
    }

    public UserDto getUserById(String id) {
        return userMapper.toUserDto(findUserById(id));
    }

    public Long getCount() {
        return repository.count();
    }

    public UserDto addUser(CreateUserRequest userRequest) {

        if (userExistsByEmail(userRequest.email()))
            throw new UserAlreadyExistsException(String.format("Email: %s is already taken!", userRequest.email()));

        User user = userMapper.toUser(userRequest);


        return userMapper.toUserDto(repository.save(user));
    }


    public void updateUser(String id, UpdateUserRequest userRequest) {
        User user = findUserById(id);
        user.setUsername(userRequest.username());
        user.setPassword(userRequest.password() != null ? userRequest.password() : user.getPassword());

        repository.save(user);
    }

    private User findUserById(String id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(String
                                .format("User with id:%s is unavailable", id)));
    }

    private boolean userExistsByEmail(String email) {
        return repository.existsByEmail(email);
    }

}
