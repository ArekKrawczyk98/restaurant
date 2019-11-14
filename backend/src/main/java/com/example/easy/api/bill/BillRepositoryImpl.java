package com.example.easy.api.bill;

import com.example.easy.domain.Bill;
import com.example.easy.domain.guest.BillRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BillRepositoryImpl implements BillRepository{

    private final BillRepositorySpringData billRepositorySpringData;

    @Override
    public Bill load(int id) {

        Optional<BillEntity> billEntity = billRepositorySpringData.findById(id);
        if (billEntity.isPresent()){

            return BillMapper.mapFromEntityToDomainModel(billEntity.get());


        }
        else {
            throw new IllegalStateException("It is null");
        }

    }

    @Override
    public void add(Bill bill) {

        BillEntity billEntity = BillMapper.mapFromDomainModelToEntity(bill);

        try{
            billRepositorySpringData.save(billEntity);


        }
        catch (Exception e){
            System.err.println(e);
        }

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
    public void delete(int id) {
        billRepositorySpringData.deleteById(id);
    }

    @Override
    public void update(int id, Bill bill) {

        add(bill);

    }

    @Override
    public void deleteAll() {
        billRepositorySpringData.deleteAll();

    }
}
