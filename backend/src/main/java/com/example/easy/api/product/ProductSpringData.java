package com.example.easy.api.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpringData extends JpaRepository<ProductEntity,Integer> {

    ProductEntity findByName(String name);
}
