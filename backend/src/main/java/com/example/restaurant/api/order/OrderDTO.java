package com.example.restaurant.api.order;

import com.example.restaurant.domain.Bill;
import com.example.restaurant.domain.order.Order;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class OrderDTO {
    private Bill bill;
    private Order order;
}
