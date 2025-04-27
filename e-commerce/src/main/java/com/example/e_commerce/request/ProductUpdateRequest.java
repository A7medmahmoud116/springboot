package com.example.e_commerce.request;

import com.example.e_commerce.model.Category;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductUpdateRequest {
    private long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}
