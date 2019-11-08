package com.example.easy;

import com.example.easy.api.bill.BillEntity;
import com.example.easy.api.bill.BillMapper;
import com.example.easy.domain.Bill;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class MappingTest {

    private final BillMapper billMapper = new BillMapper();
    @Test
    public void shouldMapEntityToDomain(){
        BillEntity billEntity = new BillEntity(1,new Date(),50.0);

        Bill domain = billMapper.mapFromEntityToDomainModel(billEntity);

        Assert.assertEquals(billEntity.getMoneyPaid(),domain.getToPay());

    }
    @Test
    public void shouldMapEntityToDomainAndTableShouldntExist(){
        BillEntity billEntity = new BillEntity(1,new Date(),50.0);

        Bill domain = billMapper.mapFromEntityToDomainModel(billEntity);

        Assert.assertNull(domain.getTable());

    }

}
