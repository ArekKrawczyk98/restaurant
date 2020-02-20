package com.example.easy.api.order;

import com.example.easy.api.product.ProductEntity;
import com.example.easy.api.product.ProductMapper;
import com.example.easy.domain.order.Order;
import com.example.easy.domain.product.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderEntity fromDomainModelToEntity(Order order,Integer billId){
        List<ProductEntity> productEntities = order.getProducts().stream().map(ProductMapper::fromDomainModelToEntity).collect(Collectors.toList());
        return new OrderEntity(Integer.valueOf(order.getId()),productEntities,billId);
    }

    public static Order fromEntityToDomainModel(OrderEntity entity){
        List<Product> products= new LinkedList<>();
        for (ProductEntity x: entity.getProductList()) {
            products.add(ProductMapper.fromEntityToDomainModel(x));
        }
        return new Order(entity.getId().toString(),products);
    }
}
