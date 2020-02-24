package com.example.easy.api.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepositorySpringData extends JpaRepository<OrderEntity,Integer> {


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into orders_products values (:orderId,:productId);")
    void saveToJoinTable(@Param("orderId") Integer orderId, @Param("productId") Integer id1);

    @Query(nativeQuery = true, value = "select * from orders where bill_id=:orderId")
    List<OrderEntity>getAllOrdersForBillId(@Param("orderId")Integer orderId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from orders where bill_id=:id")
    void deleteAllByBillId(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from orders_products where order_id=:id")
    void deleteAllByOrderIdIdFromJoinTable(@Param("id") long id);

    @Query(nativeQuery = true, value = "select id from orders where bill_id=:id")
    List<Integer> findAllOrdersIdByBillId(@Param("id") long id);
}
