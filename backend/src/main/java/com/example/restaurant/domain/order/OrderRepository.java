package com.example.restaurant.domain.order;

import java.util.List;

public interface OrderRepository {
    void add(Order order,Long billId);
    Order load(Long id);
    void remove(Long id);
    void update(Long id,Order order);
    List<Order> getAllOrdersForBillId(Long billId);

    List<Order> loadAll();

    void removeAllOrdersByBillId(Long billId);

    void deleteAll();
}
