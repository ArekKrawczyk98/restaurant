package com.example.easy.api.order;

import com.example.easy.domain.order.Order;
import com.example.easy.domain.order.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private OrderRepositorySpringData orderRepositorySpringData;
    @Override
    public void add(Order order) {
        orderRepositorySpringData.save(order);

    }

    @Override
    public Order load(String id) {
        if (orderRepositorySpringData.existsById(id)){
            Optional<Order> optional = orderRepositorySpringData.findById(id);
            if (optional.isPresent()){
                return optional.get();
            }

        }

        throw new IllegalStateException("Doesnt exist");

    }


    @Override
    public void remove(String id) {
        orderRepositorySpringData.deleteById(id);

    }

    @Override
    public void update(String id, Order order) {
        orderRepositorySpringData.save(order);

    }
}

