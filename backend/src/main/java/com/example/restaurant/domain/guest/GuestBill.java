package com.example.restaurant.domain.guest;

import com.example.restaurant.domain.Bill;
import com.example.restaurant.domain.RebatePolicyService;
import lombok.Getter;

import java.util.Date;



public class GuestBill extends Bill {


    private final String name;

    @Getter
    private final GuestPosition guestPosition;

    public GuestBill(Long id,Double toPay, Date date, Integer table, String name, GuestPosition guestPosition) {
        super(id,toPay, date, table);
        this.name = name;
        this.guestPosition = guestPosition;
    }



    public static GuestBill from(Long id,String name, Double toPay, Date date, Integer table, GuestPosition guestposition){

        return new GuestBill(id,toPay,date,table,name,guestposition);
    }

    public double billToBePaid(){
        RebatePolicyService rebatePolicyService = new RebatePolicyService(guestPosition);
        return rebatePolicyService.calculate(super.getToPay());
    }
}
