package com.example.easy.domain.invoice;

import lombok.Value;

import java.util.Date;

@Value
public class Invoice {
    private final String id;
    private final Date date;
    private final Double moneyThatNeedToBePaid;
    private final Double moneyRecieved;

    public Double getRest(){
        return moneyRecieved-moneyThatNeedToBePaid;
    }
}