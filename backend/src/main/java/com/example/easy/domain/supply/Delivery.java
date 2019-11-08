package com.example.easy.domain.supply;

import com.example.easy.domain.product.Product;
import lombok.Value;

import java.util.List;

@Value
public class Delivery {
    private final List<Product> productList;
}
