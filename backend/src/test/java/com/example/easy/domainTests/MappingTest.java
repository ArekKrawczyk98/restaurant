package com.example.easy.domainTests;

import com.example.easy.api.bill.BillEntity;
import com.example.easy.api.bill.BillMapper;
import com.example.easy.domain.Bill;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class MappingTest {

    @Test
    public void shouldMapEntityToDomain(){
        BillEntity billEntity = new BillEntity(1,new Date(),50.0,5);

        Bill domain = BillMapper.mapFromEntityToDomainModel(billEntity);

        Assert.assertEquals(billEntity.getMoneyPaid(),domain.getToPay());

    }
    @Test
    public void shouldMapEntityToDomainAndTableShouldntExist(){
        BillEntity billEntity = new BillEntity(1,new Date(),50.0,5);

        Bill domain = BillMapper.mapFromEntityToDomainModel(billEntity);

        Assert.assertEquals(1L,domain.getId().longValue());

    }

}
