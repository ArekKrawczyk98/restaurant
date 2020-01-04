package com.example.easy.domain.order;

import java.util.List;

public interface OrderRepository {
    void add(Order order);
    Order load(String id);
    void remove(String id);
    void update(String id,Order order);

    List<Order> loadAll();
}
