package com.ugurukku.tapazspring.security;

import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.exceptions.user.UserNotFoundException;
import com.ugurukku.tapazspring.repositories.UserRepository;
import com.ugurukku.tapazspring.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findUserByEmail(username);

    user.orElseThrow(() -> new UsernameNotFoundException("Username not found : "+username));

    return user.map(MyUserDetails::new).orElseThrow(() -> new UserNotFoundException("not found"));
    }
}
