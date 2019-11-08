package com.example.easy.domain;

import com.example.easy.domain.invoice.Invoice;
import lombok.AllArgsConstructor;


import java.util.Date;

@AllArgsConstructor
public class Zmiana {
    private final Date startingDate;
    private Double money;

    public void addToCurrentMoney(Invoice invoice){
        money += invoice.getMoneyThatNeedToBePaid();
    }
}
