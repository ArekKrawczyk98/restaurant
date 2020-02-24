package com.example.easy.domainTests;

import com.example.easy.domain.Bill;
import com.example.easy.domain.guest.BillRepository;

import java.util.*;

public class BillRepositoryInMemoryImpl implements BillRepository {

    private final HashMap<Long, Bill> database = new HashMap<>();
    @Override
    public Bill loadById(long id) {
        return database.get(id);
    }

    @Override
    public Long add(Bill bill) {

        database.put((long) (database.size() + 1), bill);

        return database.get((long)database.size()).getId();

    }

    @Override
    public List<Bill> loadAll() {

        return new ArrayList<>(database.values());

    }

    @Override
    public void delete(long id) {

        database.remove(id);

    }

    @Override
    public void update(long id, Bill bill) {

        database.replace(id,bill);

    }



    @Override
    public Long getIdByTableNumber(Integer table) {
 /*       for (Bill bill : database.values()) {
            if (bill.getTable().equals(table)){
                return table.getNumber();
            }


        }*/
        Optional<Long> o = database.entrySet().stream().
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



