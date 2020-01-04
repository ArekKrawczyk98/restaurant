package com.example.easy.api.order;

import com.example.easy.domain.order.Order;
import com.example.easy.domain.order.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private OrderRepositorySpringData orderRepositorySpringData;
    @Override
    public void add(Order order) {
        orderRepositorySpringData.save(OrderMapper.fromDomainModelToEntity(order));

    }

    @Override
    public Order load(String id) {

        Optional <OrderEntity> orderEntity = orderRepositorySpringData.findById(id);

        if (orderEntity.isPresent()){
            return OrderMapper.fromEntityToDomainModel(orderEntity.get());
        }
        else {
            throw new IllegalStateException("Doesnt exist");

        }

    }


    @Override
    public void remove(String id) {
        orderRepositorySpringData.deleteById(id);

    }

    @Override
    public void update(String id, Order order) {
        orderRepositorySpringData.save(OrderMapper.fromDomainModelToEntity(order));

    }

    @Override
    public List<Order> loadAll() {

        List<OrderEntity> entities = orderRepositorySpringData.findAll();
        List<Order> orderList = new LinkedList<>();

        for (OrderEntity x: entities) {
            orderList.add(OrderMapper.fromEntityToDomainModel(x));
        }
        return orderList;
    }
}

