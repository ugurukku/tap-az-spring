package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ProductConsumer {

    private final ProductRepository productRepository;

    @Autowired
    public ProductConsumer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public void consume(){
        String  url = "https://fakestoreapi.com/products";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Product[]> responseEntity =
                restTemplate.getForEntity(url, Product[].class);

        Product[] products = responseEntity.getBody();

        assert products != null;

        List<Product> products1 = Arrays
                .stream(products).toList();
        System.out.println(products1);

        productRepository.saveAll(products1);
    }

}
