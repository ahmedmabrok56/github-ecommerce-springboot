package com.dailycodework.ecommerce.controller;

import com.dailycodework.ecommerce.dto.product.ProductRequest;
import com.dailycodework.ecommerce.dto.product.ProductResponse;
import com.dailycodework.ecommerce.service.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>>getAllProducts() {
        return ResponseEntity.ok(productService.getAll()) ;
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleleAllProducts(){
         productService.deleteAll();
         return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(productService.getByCategoryId(categoryId));
    }
}