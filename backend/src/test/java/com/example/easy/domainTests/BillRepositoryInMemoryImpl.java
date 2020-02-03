package com.example.easy.domainTests;

import com.example.easy.domain.Bill;
import com.example.easy.domain.guest.BillRepository;

import java.util.*;

public class BillRepositoryInMemoryImpl implements BillRepository {

    private final HashMap<Integer, Bill> database = new HashMap<>();
    @Override
    public Bill loadById(int id) {
        return database.get(id);
    }

    @Override
    public void add(Bill bill) {

        database.put(database.size()+1, bill);

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
    public Integer getIdByTableNumber(Integer table) {
 /*       for (Bill bill : database.values()) {
            if (bill.getTable().equals(table)){
                return table.getNumber();
            }


        }*/
        Optional<Integer> o = database.entrySet().stream().
                filter(map -> map.getValue().getTable().equals(table)).
                map(Map.Entry::getKey).
                findFirst();
        if (o.isPresent()){
            return o.get();
        }
        throw new IllegalStateException("Cant find number");
    }


    @Override
    public void deleteAll() {

        database.clear();

    }

}



