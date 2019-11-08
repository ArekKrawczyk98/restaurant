package com.example.easy.domain.order;

public interface OrderRepository {
    void add(Order order);
    Order load(String id);
    void remove(String id);
    void update(String id,Order order);
}
