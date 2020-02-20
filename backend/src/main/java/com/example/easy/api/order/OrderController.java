package com.example.easy.api.order;

import com.example.easy.domain.Bill;
import com.example.easy.domain.RestaurantService;
import com.example.easy.domain.order.Order;
import com.example.easy.domain.order.OrderRepository;
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
        restaurantService.addOrder(orderDTO.getOrder(),orderDTO.getBill());
    }

    @DeleteMapping("/{id}")
    public void removeOrderWhenAdded(@PathVariable String id, @RequestParam long billId ){
        Bill bill = restaurantService.getBillById(billId);
        restaurantService.removeOrderWhenAlreadyAdded(id,bill);
    }
    @DeleteMapping
    public void removeOrder(@RequestParam String id){
        orderRepository.remove(id);
    }

    @GetMapping("/all")
    public List<Order> getAll(){
        return orderRepository.loadAll();

    }
    @GetMapping("/{id}")
    public Order getById(@PathVariable Integer id){
        return orderRepository.load(String.valueOf(id));
    }

    @GetMapping("/billId={id}")
    public List<Order> getAllOrdersForBillId(@PathVariable Long id){
        return orderRepository.getAllOrdersForBillId(id);
    }



}
