package com.example.easy.domain.guest;

import com.example.easy.domain.Bill;

import java.util.List;

public interface BillRepository {
    Bill loadById(long id);
    Long add(Bill bill);
    List<Bill> loadAll();
    void delete(long id);
    void update(long id,Bill bill);
    Long getIdByTableNumber(Integer table);

    void deleteAll();
}
