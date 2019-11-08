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
    private final BillMapper mapper;

    @Override
    public Bill load(int id) {

        Optional<BillEntity> billEntity = billRepositorySpringData.findById(id);
        if (billEntity.isPresent()){

            return mapper.mapFromEntityToDomainModel(billEntity.get());


        }
        else {
            throw new IllegalStateException("It is null");
        }

    }

    @Override
    public void add(Bill bill) {

        BillEntity billEntity = mapper.mapFromDomainModelToEntity(bill);

        billRepositorySpringData.save(billEntity);

    }

    @Override
    public List<Bill> loadAll() {
        List<BillEntity> billEntities =billRepositorySpringData.findAll();
        List<Bill> bills = new ArrayList<>();

        for (BillEntity billEntity : billEntities) {

            bills.add(mapper.mapFromEntityToDomainModel(billEntity));


        }
        return bills;
    }
}
