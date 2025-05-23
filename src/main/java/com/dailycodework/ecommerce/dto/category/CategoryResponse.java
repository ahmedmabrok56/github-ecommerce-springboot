package com.dailycodework.ecommerce.dto.category;

import com.dailycodework.ecommerce.dto.product.ProductResponse;
import lombok.Data;

import java.util.List;
@Data
public class CategoryResponse {

    private Long id;

    private String name;
    private List<ProductResponse> products;

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public CategoryResponse(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
