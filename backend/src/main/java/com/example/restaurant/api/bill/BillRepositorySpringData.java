package com.example.restaurant.api.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BillRepositorySpringData extends JpaRepository<BillEntity,Integer> {
    @Query(value = "select id from bills where table_number=:tableNumber", nativeQuery = true)
    Long findIdByTableNumber(@Param("tableNumber") Integer tableNumber);
}
