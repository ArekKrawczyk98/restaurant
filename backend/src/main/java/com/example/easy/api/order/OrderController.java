package com.example.easy.api.order;

import com.example.easy.domain.Bill;
import com.example.easy.domain.RestaurantService;
import com.example.easy.domain.order.Order;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private RestaurantService restaurantService;

    @PostMapping
    public void createOrder(@RequestBody Order order,@RequestBody Bill bill){
        restaurantService.addOrder(order,bill);
    }

    @DeleteMapping
    public void removeOrder(String id,@RequestBody Bill bill ){
        restaurantService.removeOrderWhenAlreadyAdded(id,bill);
    }


}
