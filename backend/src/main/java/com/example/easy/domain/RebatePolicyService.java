package com.example.easy.domain;

import com.example.easy.domain.guest.GuestPosition;
import lombok.Value;

@Value
public class RebatePolicyService implements RabatePolicy {

    private GuestPosition guestPosition;

    @Override
    public Double calculate(Double toPay) {
        if (guestPosition== GuestPosition.OWNER){

            toPay=toPay*0.01;

        }
        else if (guestPosition==GuestPosition.EMPLOYEE){

            toPay=toPay*0.5;

        }
        return toPay;

    }
}
