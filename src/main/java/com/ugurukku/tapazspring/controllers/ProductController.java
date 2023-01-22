package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.entities.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        String url = "https://fakestoreapi.com/products";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Product[]> responseEntity =
                restTemplate.getForEntity(url, Product[].class);

        Product[] products = responseEntity.getBody();

        assert products != null;

        List<Product> products1 = Arrays
                .stream(products).toList();

        return ResponseEntity.ok(products1);

    }

}
