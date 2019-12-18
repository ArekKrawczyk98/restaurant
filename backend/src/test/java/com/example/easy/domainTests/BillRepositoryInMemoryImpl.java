package com.example.easy.domainTests;

import com.example.easy.domain.Bill;
import com.example.easy.domain.guest.BillRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillRepositoryInMemoryImpl implements BillRepository {

    private final HashMap<Integer, Bill> database = new HashMap<>();
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

    @Override
    public void delete(int id) {

        database.remove(id);

    }

    @Override
    public void update(int id, Bill bill) {

        database.replace(id,bill);

    }

    @Override
    public void deleteAll() {

        database.clear();

    }

}



