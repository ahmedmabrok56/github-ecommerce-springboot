package com.dailycodework.ecommerce.service.service;

import com.dailycodework.ecommerce.dto.category.CategoryRequest;
import com.dailycodework.ecommerce.dto.category.CategoryResponse;
import com.dailycodework.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse save(CategoryRequest request);
    List<CategoryResponse> getAll();
    CategoryResponse getById(Long id);
    void delete(Long id);

    CategoryResponse update(Long id, CategoryRequest request);

    void deleteAll();
}
