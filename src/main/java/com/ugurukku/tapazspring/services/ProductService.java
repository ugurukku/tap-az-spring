package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.dto.product.ProductAllResponse;
import com.ugurukku.tapazspring.dto.product.ProductMapper;
import com.ugurukku.tapazspring.dto.product.ProductRequest;
import com.ugurukku.tapazspring.dto.product.ProductResponse;
import com.ugurukku.tapazspring.entities.Category;
import com.ugurukku.tapazspring.entities.City;
import com.ugurukku.tapazspring.entities.Product;
import com.ugurukku.tapazspring.exceptions.product.ProductNotFoundException;
import com.ugurukku.tapazspring.repositories.ProductRepository;
import org.springframework.stereotype.Service;

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

    public List<ProductAllResponse> getAllByCategoryId(Long id) {
        return productRepository.findAllByCategory_Id(id).stream().map(productMapper::toProductAllResponse).toList();
    }

    public ProductResponse getProductById(Long id) {
        return productMapper.toProductResponse(productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id:%s not found!", id))));
    }

    public String removeProductById(Long id) {

        String title = getProductById(id).title();
        productRepository.deleteById(id);
        imageDataService.removeImageByProductId(id);


        return title + " successfully deleted.";
    }

    public Long addProduct(ProductRequest productRequest) {
       return productRepository.save(new Product(
                productRequest.title(),
                productRequest.price(), productRequest.description(),
                productRequest.userEmail(),
                new Category(productRequest.category().id()),
                new City(productRequest.city().id())
        )).getId();

    }

    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }


    public List<ProductAllResponse>  getAllByUserId(String userId) {
        return productRepository.findAllByUserId(userId);
    }
}
