package com.example.easy.api.bill;

import com.example.easy.domain.Bill;
import com.example.easy.domain.RestaurantService;
import com.example.easy.domain.guest.BillRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bills/")
public class BillController {

    private RestaurantService restaurantService;
    private BillRepository billRepository;

    public BillController(RestaurantService restaurantService,BillRepository billRepository) {
        this.restaurantService = restaurantService;
        this.billRepository = billRepository;
    }


    @GetMapping("{id}")
    public Bill getById(@PathVariable int id){

        return restaurantService.getBillById(id);
    }

    @GetMapping("all")
    public List<Bill> getAll(){

        return billRepository.loadAll();
    }






}
