package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.entities.City;
import com.ugurukku.tapazspring.services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CityController {

    private final CityService service;


    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(service.getAll());
    }
}
