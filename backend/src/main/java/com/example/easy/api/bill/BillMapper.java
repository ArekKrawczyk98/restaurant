package com.example.easy.api.bill;

import com.example.easy.domain.Bill;
import com.example.easy.domain.Table;


public class BillMapper {


    public static BillEntity mapFromDomainModelToEntity(Bill domain){
        BillEntity billEntity = new BillEntity();
        billEntity.setDate(domain.getDate());
        billEntity.setMoneyPaid(domain.getToPay());
        billEntity.setTableNumber(domain.getTable().getNumber());
        return billEntity;

    }
    public static Bill mapFromEntityToDomainModel(BillEntity entity){


        return new Bill(entity.getMoneyPaid(),entity.getDate(),new Table(entity.getTableNumber()));

    }



}
