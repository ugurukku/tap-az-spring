package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.dto.product.ProductAllResponse;
import com.ugurukku.tapazspring.dto.product.ProductRequest;
import com.ugurukku.tapazspring.entities.Product;
import com.ugurukku.tapazspring.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductAllResponse>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(productService.removeProductById(id));
    }

}
