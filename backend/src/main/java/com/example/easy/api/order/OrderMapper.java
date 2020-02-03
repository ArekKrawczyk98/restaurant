package com.example.easy.api.order;

import com.example.easy.api.product.ProductEntity;
import com.example.easy.api.product.ProductMapper;
import com.example.easy.domain.order.Order;
import com.example.easy.domain.product.Product;

import java.util.LinkedList;
import java.util.List;

public class OrderMapper {
    public static OrderEntity fromDomainModelToEntity(Order order){
        List<ProductEntity> productEntities = new LinkedList<>();
        for (Product x: order.getList()) {
            productEntities.add(ProductMapper.fromDomainModelToEntity(x));
        }
        return new OrderEntity(Integer.valueOf(order.getId()),productEntities);
    }

    public static Order fromEntityToDomainModel(OrderEntity entity){
        List<Product> products= new LinkedList<>();
        for (ProductEntity x: entity.getProductList()) {
            products.add(ProductMapper.fromEntityToDomainModel(x));
        }
        return new Order(entity.getId().toString(),products);
    }
}
