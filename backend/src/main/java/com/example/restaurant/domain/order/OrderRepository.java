package com.example.restaurant.domain.order;

import java.util.List;

public interface OrderRepository {
    void add(Order order,Integer billId);
    Order load(String id);
    void remove(String id);
    void update(String id,Order order);
    List<Order> getAllOrdersForBillId(Long billId);

    List<Order> loadAll();

    void removeAllOrdersByBillId(long id);

    void deleteAll();
}
