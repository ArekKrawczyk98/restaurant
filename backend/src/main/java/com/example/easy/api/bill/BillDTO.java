package com.example.easy.api.bill;

import com.example.easy.domain.Bill;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class BillDTO {
    Bill bill;
    Double money;
}
