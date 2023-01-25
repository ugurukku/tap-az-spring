package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.entities.City;
import com.ugurukku.tapazspring.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public void saveAll(List<City> cities){
        repository.saveAll(cities);
    }

}
