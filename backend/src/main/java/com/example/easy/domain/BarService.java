package com.example.easy.domain;

import com.example.easy.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BarService {
    private List<Product> barOrders;
    private Double moneyFromOrders;


    public void addToOrders(List<Product> order){
        barOrders.addAll(order);
    }

    public void done(String id){
        if (barOrders.contains(barOrders.get(Integer.parseInt(id)))){
            moneyFromOrders+=barOrders.get(Integer.parseInt(id)).getCost();
            barOrders.remove(Integer.parseInt(id));
        }
        else {
            throw new IllegalStateException("There is no order");
        }
    }
}
