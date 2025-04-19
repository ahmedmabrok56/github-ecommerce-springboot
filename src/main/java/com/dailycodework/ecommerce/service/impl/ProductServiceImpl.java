package com.dailycodework.ecommerce.service.impl;

import com.dailycodework.ecommerce.dto.product.ProductRequest;
import com.dailycodework.ecommerce.dto.product.ProductResponse;
import com.dailycodework.ecommerce.exception.ResourceNotFoundException;
import com.dailycodework.ecommerce.exception.ResourceNotFoundException;
import com.dailycodework.ecommerce.model.Category;
import com.dailycodework.ecommerce.model.Product;
import com.dailycodework.ecommerce.repository.CategoryRepository;
import com.dailycodework.ecommerce.repository.ProductRepository;
import com.dailycodework.ecommerce.service.service.ProductService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Transactional
    @Override
    public ProductResponse save(ProductRequest request) {
        Product product = modelMapper.map(request, Product.class);

        if (request.getCategory().getId()!= null) {
            Category category = categoryRepository.findById(request.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            product.setCategory(category);
        }

        Product savedProduct=productRepository.save(product);

        ProductResponse response = modelMapper.map(savedProduct,ProductResponse.class);

        if (savedProduct.getCategory() != null) {
            response.setCategoryName(savedProduct.getCategory().getName());
            response.setCategoryId(savedProduct.getCategory().getId());
        }




        return response;    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not found"));

        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not found"));

      /*  product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
*/
        modelMapper.map(request, product);
        if(request.getCategory().getId() !=null){

        Category category=categoryRepository.findById(request.getCategory().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        }

        return modelMapper.map(productRepository.save(product), ProductResponse.class);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public List<ProductResponse> getByCategoryId(Long categoryId) {
       List<Product> products= productRepository.findByCategoryId(categoryId);
       return products.stream()
               .map(product -> {
                   ProductResponse response =modelMapper.map(product,ProductResponse.class);
                   response.setCategoryId(product.getCategory().getId());
                   response.setCategoryName(product.getCategory().getName());
                   return response;
               })
               .collect(Collectors.toList());
    }
}
