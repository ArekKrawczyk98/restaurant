package com.example.easy.domain.guest;

import com.example.easy.domain.Bill;

import java.util.List;

public interface BillRepository {
    Bill loadById(int id);
    void add(Bill bill);
    List<Bill> loadAll();
    void delete(int id);
    void update(int id,Bill bill);
    Integer getIdByTableNumber(Integer table);

    void deleteAll();
}
