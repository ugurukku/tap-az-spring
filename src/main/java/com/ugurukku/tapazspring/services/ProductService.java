package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.entities.Product;
import com.ugurukku.tapazspring.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {

        return productRepository.findAll();
    }

}
