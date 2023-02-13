package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.dto.user.*;
import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.exceptions.user.AuthenticationFailedException;
import com.ugurukku.tapazspring.exceptions.user.UserAlreadyExistsException;
import com.ugurukku.tapazspring.exceptions.user.UserNotFoundException;
import com.ugurukku.tapazspring.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, UserMapper userMapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.encoder = encoder;
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

    public User addUser(CreateUserRequest userRequest) {

        if (userExistsByEmail(userRequest.email()))
            throw new UserAlreadyExistsException(String.format("Email: %s is already taken!", userRequest.email()));

        User user = userMapper.toUser(userRequest);
        user.setPassword(encoder.encode(user.getPassword()));



    }


    public void updateUser(String id, UpdateUserRequest userRequest) {
        User user = findUserById(id);
        user.setUsername(userRequest.username() != null ? userRequest.username() : user.getUsername());
        user.setPassword(userRequest.password() != null ? encoder.encode(userRequest.password()) : user.getPassword());
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

    public User authenticate(UserLoginDto userLoginDto) {

        User user = getUserByEmail(userLoginDto.email());

        if (!(encoder.matches(userLoginDto.password(), user.getPassword()))){
            throw  new AuthenticationFailedException("E poçt və ya şifrə yanlışdır!");
        }
        user.setPassword(userLoginDto.password());
        return user;
    }
}
