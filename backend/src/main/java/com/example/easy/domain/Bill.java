package com.example.easy.domain;

import com.example.easy.domain.order.Order;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.util.Date;

@Value
@NonFinal
public class Bill {

    @NonFinal
    private Double toPay;
    private final Date date;
    private final Table table;



    public void addToPay(Order order){

        double orderValue= order.priceOfAllProducts();
        toPay+=orderValue;

    }


    public void subtractMoney(Double moneyFromOrder) {
        toPay-=moneyFromOrder;
    }

    protected double billToBePaid(){
        return toPay;
    }
}
