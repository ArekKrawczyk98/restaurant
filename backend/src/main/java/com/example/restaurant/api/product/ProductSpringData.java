package com.example.restaurant.api.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSpringData extends JpaRepository<ProductEntity,Integer> {

    ProductEntity findByName(String name);
    List<ProductEntity> findByOrderByIdAsc();
}
