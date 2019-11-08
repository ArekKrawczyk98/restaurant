package com.example.easy.domain.kitchen;

import com.example.easy.domain.order.Order;
import com.example.easy.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class KitchenService {
    private List<Product> kitchenOrders;
    private Double moneyFromOrders;


    public void addToOrders(List<Product> order){
        kitchenOrders.addAll(order);
    }

    public void done(String id){
        if (kitchenOrders.contains(kitchenOrders.get(Integer.parseInt(id)))){
            moneyFromOrders+=kitchenOrders.get(Integer.parseInt(id)).getCost();
            kitchenOrders.remove(Integer.parseInt(id));
        }
        else {
            throw new IllegalStateException("There is no order");
        }
    }

}
