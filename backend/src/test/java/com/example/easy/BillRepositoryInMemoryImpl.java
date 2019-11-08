package com.example.easy;

import com.example.easy.domain.Bill;
import com.example.easy.domain.guest.BillRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillRepositoryInMemoryImpl implements BillRepository {

    private final HashMap<String, Bill> database = new HashMap<>();
    @Override
    public Bill load(int id) {
        return database.get(id);
    }

    @Override
    public void add(Bill bill) {

        database.put(bill.getTable().getNumber(), bill);

    }

    @Override
    public List<Bill> loadAll() {

        return new ArrayList<>(database.values());

    }

}



