package com.example.restaurant.api.bill;

import com.example.restaurant.domain.Bill;
import com.example.restaurant.domain.product.Product;
import com.example.restaurant.domain.tableBill.TableBill;
import lombok.Value;

import java.util.List;

@Value
public class SplitBillDTO {
    private final List<Product> products;
    private final TableBill bill;
}
