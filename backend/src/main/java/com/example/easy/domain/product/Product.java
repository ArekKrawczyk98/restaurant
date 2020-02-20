package com.example.easy.domain.product;

import lombok.Value;

@Value
public class Product {
    private final Integer id;
    private final String name;
    private final Double cost;
    private final ProductCategory category;
}
