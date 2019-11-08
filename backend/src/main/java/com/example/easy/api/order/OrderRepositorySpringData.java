package com.example.easy.api.order;

import com.example.easy.domain.order.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepositorySpringData extends CrudRepository<Order,String> {
}
