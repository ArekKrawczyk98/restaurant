package com.example.restaurant.api.bill;

import com.example.restaurant.domain.Bill;
import com.example.restaurant.domain.guest.BillRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BillRepositoryImpl implements BillRepository {

    private final BillRepositorySpringData billRepositorySpringData;

    @Override
    public Bill loadById(long id) {

        Optional<BillEntity> billEntity = billRepositorySpringData.findById(Math.toIntExact(id));
        if (billEntity.isPresent()){

            return BillMapper.mapFromEntityToDomainModel(billEntity.get());


        }
        else {
            throw new IllegalStateException("It is null");
        }

    }

    @Override
    public Long add(Bill bill) {

        BillEntity billEntity = BillMapper.mapFromDomainModelToEntity(bill);
        Long id = 0L;

        try{
          id = Long.valueOf(billRepositorySpringData.save(billEntity).getId());


        }
        catch (Exception e){
            System.err.println();
        }
        return id;

    }

    @Override
    public List<Bill> loadAll() {
        List<BillEntity> billEntities =billRepositorySpringData.findAll();
        List<Bill> bills = new ArrayList<>();

        for (BillEntity billEntity : billEntities) {

            bills.add(BillMapper.mapFromEntityToDomainModel(billEntity));


        }
        return bills;
    }

    @Override
    public void delete(long id) {
        billRepositorySpringData.deleteById(Math.toIntExact(id));
    }

    @Override
    public void update(long id, Bill bill) {
        add(bill);
    }

    @Override
    public Long getIdByTableNumber(Integer table) {
      return  billRepositorySpringData.findIdByTableNumber(table);
    }


    @Override
    public void deleteAll() {
        billRepositorySpringData.deleteAll();

    }
}
