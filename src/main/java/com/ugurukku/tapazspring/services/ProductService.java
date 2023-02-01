package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.dto.product.ProductAllResponse;
import com.ugurukku.tapazspring.dto.product.ProductMapper;
import com.ugurukku.tapazspring.dto.product.ProductRequest;
import com.ugurukku.tapazspring.entities.Product;
import com.ugurukku.tapazspring.exceptions.product.ProductNotFoundException;
import com.ugurukku.tapazspring.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ImageDataService imageDataService;

    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ImageDataService imageDataService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.imageDataService = imageDataService;
        this.productMapper = productMapper;
    }

    public List<ProductAllResponse> getAll() {

        return productRepository.findAll().stream().map(productMapper::toProductAllResponse).toList();
    }

    public ProductAllResponse getProductById(Long id) {
        return productMapper.toProductAllResponse(productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id:%s not found!", id))));
    }

    public String removeProductById(Long id){

        String title = getProductById(id).title();
        productRepository.deleteById(id);

        return title + " successfully deleted.";
    }

    public void addProduct(ProductRequest productRequest) throws IOException {
       Product product = productRepository.save(productMapper.toProduct(productRequest));
       imageDataService.uploadImage(product.getId(),productRequest.file());

    }

    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }
}
