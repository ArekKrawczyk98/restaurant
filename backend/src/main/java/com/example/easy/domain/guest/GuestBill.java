package com.example.easy.domain.guest;

import com.example.easy.domain.Bill;
import com.example.easy.domain.RabatePolicyService;
import lombok.Getter;

import java.util.Date;



public class GuestBill extends Bill {


    private final String name;

    @Getter
    private final GuestPosition guestPosition;

    public GuestBill(Double toPay, Date date, Integer table, String name, GuestPosition guestPosition) {
        super(toPay, date, table);
        this.name = name;
        this.guestPosition = guestPosition;
    }



    public static GuestBill from(String name, Double toPay, Date date, Integer table, GuestPosition guestposition){

        return new GuestBill(toPay,date,table,name,guestposition);
    }

    public double billToBePaid(){
        RabatePolicyService rabatePolicyService = new RabatePolicyService(guestPosition);
        return rabatePolicyService.calculate(super.getToPay());
    }
}
