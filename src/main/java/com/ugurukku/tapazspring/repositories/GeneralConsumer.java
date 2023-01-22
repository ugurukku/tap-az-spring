package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.entities.Category;
import com.ugurukku.tapazspring.entities.City;
import com.ugurukku.tapazspring.entities.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class GeneralConsumer {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final CityRepository cityRepository;

    public GeneralConsumer(CategoryRepository categoryRepository, ProductRepository productRepository, CityRepository cityRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.cityRepository = cityRepository;
    }

    @Bean
    public void consume() {
        final String URL = "https://fakestoreapi.com/products";

        RestTemplate restTemplate = new RestTemplate();
        ProductFromConsumer[] products = restTemplate.getForEntity(URL, ProductFromConsumer[].class).getBody();

        assert products != null;
        List<ProductFromConsumer> products1 = Arrays.stream(products).toList();

        List<Category> categories = products1
                .stream()
                .map(ProductFromConsumer::getCategory)
                .collect(Collectors.toSet())
                .stream()
                .map(name -> new Category(null, name))
                .toList();

        categoryRepository.saveAll(categories);

        //Products
        List<Product> productList = new ArrayList<>();

        for (ProductFromConsumer product : products1) {
            productList
                    .add(
                            new Product(
                                    product.getTitle(),
                                    product.getPrice(),
                                    product.getDescription(),
                                    new Category(
                                            categoryRepository.findCategoryByName(product.getCategory()).get().getId(),
                                            null),
                                    product.getImage()));
        }

        productRepository.saveAll(productList);

    }

    @Bean
    public void initializeCities(){

        List<City> cities = List.of(
                new City(null,""),
                new City(null,"")
        );

    }

}
