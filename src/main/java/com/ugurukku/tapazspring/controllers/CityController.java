package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.exceptions.user.entities.City;
import com.ugurukku.tapazspring.services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CityController {

    private final CityService service;


    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities(){
        return ResponseEntity.ok(service.getAll());
    }
}
