package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByVerificationCode(String verificationCode);

}
