package com.dailycodework.ecommerce.service.impl;

import com.dailycodework.ecommerce.dto.category.CategoryRequest;
import com.dailycodework.ecommerce.dto.category.CategoryResponse;
import com.dailycodework.ecommerce.exception.ResourceNotFoundException;
import com.dailycodework.ecommerce.model.Category;
import com.dailycodework.ecommerce.repository.CategoryRepository;
import com.dailycodework.ecommerce.service.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponse save(CategoryRequest request) {
        Category category = modelMapper.map(request, Category.class);
        Category saved = categoryRepository.save(category);
        return modelMapper.map(saved, CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category =categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found "));

        category.setName(request.getName());

        return modelMapper.map(categoryRepository.save(category) , CategoryResponse.class);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}