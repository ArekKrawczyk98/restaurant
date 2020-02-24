package com.example.restaurant.api.bill;

import com.example.restaurant.domain.Bill;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class BillDTO {
    Bill bill;
    Double money;
}
