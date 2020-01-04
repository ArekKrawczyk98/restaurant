package com.example.easy.api.order;

import com.example.easy.domain.Bill;
import com.example.easy.domain.RestaurantService;
import com.example.easy.domain.order.Order;
import com.example.easy.domain.order.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private RestaurantService restaurantService;
    private OrderRepository orderRepository;

    @PostMapping
    public void createOrder(@RequestBody Order order,@RequestBody Bill bill){
        restaurantService.addOrder(order,bill);
    }

    @DeleteMapping("/{id}")
    public void removeOrder(@PathVariable String id,@RequestBody Bill bill ){
        restaurantService.removeOrderWhenAlreadyAdded(id,bill);
    }

    @GetMapping("/all")
    public List<Order> getAll(){
        return orderRepository.loadAll();

    }
    @GetMapping("{id}")
    public Order getById(@PathVariable String id){
        return orderRepository.load(id);
    }



}
