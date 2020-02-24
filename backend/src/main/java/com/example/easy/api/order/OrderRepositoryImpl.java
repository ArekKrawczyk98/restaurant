package com.example.easy.api.order;

import com.example.easy.domain.order.Order;
import com.example.easy.domain.order.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private OrderRepositorySpringData orderRepositorySpringData;
    @Override
    public void add(Order order,Integer billId) {

        OrderEntity entity = OrderMapper.fromDomainModelToEntity(order,billId);

        orderRepositorySpringData.save(entity);

    }

    @Override
    public Order load(String id) {

        Optional <OrderEntity> orderEntity = orderRepositorySpringData.findById(Integer.parseInt(id));

        if (orderEntity.isPresent()){
            return OrderMapper.fromEntityToDomainModel(orderEntity.get());
        }
        else {
            throw new IllegalStateException("Doesnt exist");

        }

    }


    @Override
    public void remove(String id) {
        orderRepositorySpringData.deleteById(Integer.parseInt(id));

    }

    @Override
    public void update(String id, Order order) {
        orderRepositorySpringData.save(OrderMapper.fromDomainModelToEntity(order, Integer.valueOf(id)));

    }

    @Override
    public List<Order> getAllOrdersForBillId(Long billId) {
        List <OrderEntity> entities = orderRepositorySpringData.getAllOrdersForBillId(Math.toIntExact(billId));

        return entities.stream().map(OrderMapper::fromEntityToDomainModel).collect(Collectors.toList());

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

    @Override
    public void removeAllOrdersByBillId(long id) {
        List<Integer> listOfIds = orderRepositorySpringData.findAllOrdersIdByBillId(id);
        orderRepositorySpringData.deleteAllByBillId(id);
        for (int x :listOfIds) {
            orderRepositorySpringData.deleteAllByOrderIdIdFromJoinTable(x);

        }

    }
}

