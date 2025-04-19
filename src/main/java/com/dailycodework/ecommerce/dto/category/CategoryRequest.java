package com.dailycodework.ecommerce.dto.category;

import lombok.Data;

@Data
public class CategoryRequest {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    

    public CategoryRequest(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
