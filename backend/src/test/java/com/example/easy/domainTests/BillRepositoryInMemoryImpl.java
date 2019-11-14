package com.example.easy.domainTests;

import com.example.easy.domain.Bill;
import com.example.easy.domain.guest.BillRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillRepositoryInMemoryImpl implements BillRepository {

    private final HashMap<String, Bill> database = new HashMap<>();
    @Override
    public Bill load(int id) {
        return database.get(String.valueOf(id));
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

        database.remove(Integer.toString(id));

    }

    @Override
    public void update(int id, Bill bill) {

        database.replace(String.valueOf(id),bill);

    }

    @Override
    public void deleteAll() {

        database.clear();

    }

}



