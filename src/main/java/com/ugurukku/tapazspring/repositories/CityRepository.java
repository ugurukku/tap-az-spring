package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
