package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.exceptions.user.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findCategoryByName(String name);

}
