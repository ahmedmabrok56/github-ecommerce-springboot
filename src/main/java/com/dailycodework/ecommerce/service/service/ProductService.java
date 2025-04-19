package com.dailycodework.ecommerce.service.service;

import com.dailycodework.ecommerce.dto.product.ProductRequest;
import com.dailycodework.ecommerce.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse save(ProductRequest request);
    List<ProductResponse> getAll();
    ProductResponse getById(Long id);
    ProductResponse update(Long id , ProductRequest request);

    void delete(Long id);

    void deleteAll();
    List<ProductResponse> getByCategoryId(Long categoryId);


}
