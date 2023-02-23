package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.dto.product.ProductAllResponse;
import com.ugurukku.tapazspring.dto.product.ProductRequest;
import com.ugurukku.tapazspring.dto.product.ProductResponse;
import com.ugurukku.tapazspring.entities.Product;
import com.ugurukku.tapazspring.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ProductAllResponse>> getAll(@RequestParam(value = "category",required = false) Long id,@RequestParam(value = "user",required = false) String userId) {
        if (id != null) {
            return ResponseEntity.ok(productService.getAllByCategoryId(id));
        }else if (userId!=null){
            return ResponseEntity.ok(productService.getAllByUserId(userId));
        }
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest product) throws IOException {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.removeProductById(id));
    }

}
