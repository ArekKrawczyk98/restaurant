package com.example.easy.api.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface InvoiceRepositorySpringData extends JpaRepository<InvoiceEntity,String> {
    InvoiceEntity findByDate(Date date);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO invoices values (:id,:date,:money_given,:money_received);")
    void save(@Param("id")String id,
                       @Param("date")Date date,
                       @Param("money_given")Double moneyGiven,
                       @Param("money_received") Double moneyReceived);
}
