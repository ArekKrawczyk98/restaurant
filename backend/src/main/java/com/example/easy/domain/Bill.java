package com.example.easy.domain;

import com.example.easy.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@RequiredArgsConstructor
public class Bill {
    @Getter
    private Double toPay;
    @Getter
    private final Date date;
    @Getter
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
