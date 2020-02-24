package com.example.restaurant.domain;

import com.example.restaurant.domain.order.Order;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.io.Serializable;
import java.util.Date;

@Value
@NonFinal
public class Bill implements Serializable {

    private Long id;
    @NonFinal
    private Double toPay;
    private final Date date;
    private final Integer table;



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
