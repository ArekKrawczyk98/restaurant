package com.example.easy.domain.tableBill;


import com.example.easy.domain.Bill;
import com.example.easy.domain.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TableBill extends Bill {


    public TableBill(Double toPay, Date date, Table table) {
        super(toPay, date, table);
    }

    public static TableBill from(Double toPay,Date date, Table table){
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
