package com.example.easy.api.bill;

import com.example.easy.domain.Bill;


public class BillMapper {


    public static BillEntity mapFromDomainModelToEntity(Bill domain){
        BillEntity billEntity = new BillEntity();
        billEntity.setId(Math.toIntExact(domain.getId()));
        billEntity.setDate(domain.getDate());
        billEntity.setMoneyPaid(domain.getToPay());
        billEntity.setTableNumber(domain.getTable());
        return billEntity;

    }
    public static Bill mapFromEntityToDomainModel(BillEntity entity){


        return new Bill(Long.valueOf(entity.getId()),entity.getMoneyPaid(),entity.getDate(),entity.getTableNumber());

    }



}
