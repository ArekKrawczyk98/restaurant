package com.example.easy.domain.guest;

import com.example.easy.domain.Bill;

import java.util.List;

public interface BillRepository {
    Bill load(int id);
    void add(Bill bill);
    List<Bill> loadAll();
}
