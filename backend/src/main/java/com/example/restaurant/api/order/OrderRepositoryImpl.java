package com.example.restaurant.api.order;

import com.example.restaurant.domain.order.Order;
import com.example.restaurant.domain.order.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private OrderRepositorySpringData orderRepositorySpringData;
    @Override
    public void add(Order order,Long billId) {

        OrderEntity entity = OrderMapper.fromDomainModelToEntity(order, Math.toIntExact(billId));

        orderRepositorySpringData.save(entity);

    }

    @Override
    public Order load(Long id) {

        Optional <OrderEntity> orderEntity = orderRepositorySpringData.findById(Integer.parseInt(id.toString()));

        if (orderEntity.isPresent()){
            return OrderMapper.fromEntityToDomainModel(orderEntity.get());
        }
        else {
            throw new IllegalStateException("Doesnt exist");

        }

    }


    @Override
    public void remove(Long id) {
        orderRepositorySpringData.deleteById(Math.toIntExact(id));

    }

    @Override
    public void update(Long id, Order order) {
        orderRepositorySpringData.save(OrderMapper.fromDomainModelToEntity(order, Math.toIntExact(id)));

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
    public void removeAllOrdersByBillId(Long id) {
        List<Integer> listOfIds = orderRepositorySpringData.findAllOrdersIdByBillId(id);
        orderRepositorySpringData.deleteAllByBillId(id);
        for (int x :listOfIds) {
            orderRepositorySpringData.deleteAllByOrderIdIdFromJoinTable(x);

        }

    }

    @Override
    public void deleteAll() {
        orderRepositorySpringData.deleteAll();
    }
}

