package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.dto.product.ProductFromConsumer;
import com.ugurukku.tapazspring.entities.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class GeneralConsumer {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public GeneralConsumer(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Bean
    public void consume() {
        String url = "https://fakestoreapi.com/products";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ProductFromConsumer[]> responseEntity =
                restTemplate.getForEntity(url, ProductFromConsumer[].class);

        ProductFromConsumer[] products = responseEntity.getBody();

        assert products != null;

        List<ProductFromConsumer> products1 = Arrays
                .stream(products).toList();

        Set<String> categoryNames = products1.stream().map(ProductFromConsumer::getCategory).collect(Collectors.toSet());

        List<Category> categories = categoryNames.stream().map(Category::new).toList();

        categoryRepository.saveAll(categories);

    }

}
