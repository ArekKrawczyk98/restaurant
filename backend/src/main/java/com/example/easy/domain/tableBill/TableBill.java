package com.example.easy.domain.tableBill;


import com.example.easy.domain.Bill;

import java.util.Date;


public class TableBill extends Bill {


    public TableBill(Double toPay, Date date, Integer table) {
        super(toPay, date, table);
    }

    public static TableBill from(Double toPay,Date date, Integer table){
        return new TableBill(toPay,date,table);
    }

    public Bill splitTableBill(Double pay){
        if (getToPay()>pay){
            this.subtractMoney(pay);
            return new Bill(pay,getDate(),getTable());

        }


        else throw new IllegalStateException("Incorrect number");

    }
}
