package com.example.easy.api.bill;

import com.example.easy.domain.Table;

import lombok.Value;

import java.util.Date;

@Value
public class BillDTO {
    private final Double paid;
    private final Date date;
    private final Table table;
}
