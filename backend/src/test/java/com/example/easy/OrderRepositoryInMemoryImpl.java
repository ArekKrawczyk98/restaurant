package com.example.easy;

import com.example.easy.domain.order.Order;
import com.example.easy.domain.order.OrderRepository;

import java.util.HashMap;

public class OrderRepositoryInMemoryImpl implements OrderRepository {

    private final HashMap<String, Order> database = new HashMap<>();

    @Override
    public void add(Order order) {
        database.put(order.getId(),order);

    }

    @Override
    public Order load(String id) {

        return database.get(id);
    }

    @Override
    public void remove(String id) {
        database.remove(id);

    }

    @Override
    public void update(String idToBeReplaced,Order order) {
        database.replace(idToBeReplaced,order);

    }
}
