package com.example.easy.api.order;

import com.example.easy.domain.Bill;
import com.example.easy.domain.order.Order;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class OrderDTO {
    private Bill bill;
    private Order order;
}
