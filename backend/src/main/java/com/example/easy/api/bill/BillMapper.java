package com.example.easy.api.bill;

import com.example.easy.domain.Bill;


public class BillMapper {

   /* public static Bill mapFromDTOToDomainModel(BillDTO dto){
        return new Bill(dto.getPaid(),dto.getDate(),dto.getTable());

    }*/
    public static BillEntity mapFromDomainModelToEntity(Bill domain){
        BillEntity billEntity = new BillEntity();
        billEntity.setDate(domain.getDate());
        billEntity.setMoneyPaid(domain.getToPay());
        return billEntity;

    }
    public static Bill mapFromEntityToDomainModel(BillEntity entity){


        return new Bill(entity.getMoneyPaid(),entity.getDate(),null);

    }

}
