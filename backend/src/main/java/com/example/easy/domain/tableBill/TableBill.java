package com.example.easy.domain.tableBill;


import com.example.easy.domain.Bill;

import java.util.Date;


public class TableBill extends Bill {


    public TableBill(Long id,Double toPay, Date date, Integer table) {
        super(id,toPay, date, table);
    }

    public static TableBill from(Long id,Double toPay,Date date, Integer table){
        return new TableBill(id,toPay,date,table);
    }

    public Bill splitTableBill(Double pay){
        if (getToPay()>pay){
            this.subtractMoney(pay);
            return new Bill(getId()+10000L,pay,getDate(),getTable());

        }


        else throw new IllegalStateException("Incorrect number");

    }
}
