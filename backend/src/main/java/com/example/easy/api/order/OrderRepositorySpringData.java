package com.example.easy.api.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositorySpringData extends JpaRepository<OrderEntity,String> {
}
