package com.example.restaurant.domainTests;

import com.example.restaurant.domain.order.Order;
import com.example.restaurant.domain.order.OrderRepository;

import java.util.HashMap;
import java.util.List;

public class OrderRepositoryInMemoryImpl implements OrderRepository {

    private final HashMap<Long, Order> database = new HashMap<>();

    @Override
    public void add(Order order,Long billId) {
        database.put(order.getId(),order);

    }

    @Override
    public Order load(Long id) {

        return database.get(id);
    }

    @Override
    public void remove(Long id) {
        database.remove(id);

    }

    @Override
    public void update(Long idToBeReplaced,Order order) {
        database.replace(idToBeReplaced,order);

    }

    @Override
    public List<Order> getAllOrdersForBillId(Long billId) {
        return null;
    }

    @Override
    public List<Order> loadAll() {
        return (List<Order>) database.values();
    }

    @Override
    public void removeAllOrdersByBillId(Long id) {
        //cannot implement this without bills database

    }

    @Override
    public void deleteAll() {
        database.clear();

    }
}
