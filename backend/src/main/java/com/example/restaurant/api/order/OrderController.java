package com.example.restaurant.api.order;

import com.example.restaurant.domain.Bill;
import com.example.restaurant.domain.RestaurantService;
import com.example.restaurant.domain.order.Order;
import com.example.restaurant.domain.order.OrderRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
    public void createOrder(@NotNull @RequestBody OrderDTO orderDTO){
        if (orderDTO.getOrder().getProducts().size() == 0){
            throw new IllegalStateException("Cannot add empty order");
        }
        else {
        restaurantService.addOrder(orderDTO.getOrder(),orderDTO.getBill());
        }
    }

    @DeleteMapping("/{id}")
    public void removeOrderWhenAdded(@PathVariable Long id, @RequestParam long billId ){
        Bill bill = restaurantService.getBillById(billId);
        restaurantService.removeOrderWhenAlreadyAdded(id,bill);
    }
    @DeleteMapping
    public void removeOrder(@RequestParam Long id){
        orderRepository.remove(id);
    }

    @GetMapping("/all")
    public List<Order> getAll(){
        return orderRepository.loadAll();

    }
    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id){
        return orderRepository.load(id);
    }

    @GetMapping("/billId={id}")
    public List<Order> getAllOrdersForBillId(@PathVariable Long id){
        return orderRepository.getAllOrdersForBillId(id);
    }



}
