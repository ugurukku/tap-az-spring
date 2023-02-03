package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.exceptions.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByEmail(String email);

}
