package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.dto.product.ProductAllResponse;
import com.ugurukku.tapazspring.dto.product.ProductRequest;
import com.ugurukku.tapazspring.entities.Product;
import com.ugurukku.tapazspring.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
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
    public ResponseEntity<ProductAllResponse> getById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody ProductRequest product) throws IOException {
        System.out.println(product);
        productService.addProduct(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(productService.removeProductById(id));
    }

}
