package com.example.easy.api.bill;

import com.example.easy.domain.Bill;
import com.example.easy.domain.RestaurantService;
import com.example.easy.domain.guest.BillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bills")
@CrossOrigin(origins = "http://localhost:4200")
public class BillController {

    private RestaurantService restaurantService;
    private BillRepository billRepository;

    public BillController(RestaurantService restaurantService,BillRepository billRepository) {
        this.restaurantService = restaurantService;
        this.billRepository = billRepository;
    }


    @GetMapping("/{id}")
    public Bill getById(@PathVariable int id){

        return restaurantService.getBillById(id);
    }

    @GetMapping("/all")
    public List<Bill> getAll(){

        return billRepository.loadAll();
    }

    @PostMapping
    public void addBill(@RequestBody BillEntity bill){
        restaurantService.addBill(BillMapper.mapFromEntityToDomainModel(bill));
    }

    @PutMapping("{id}")
    public Integer updateBill(@PathVariable int id,Bill bill){

       return restaurantService.updateBill(id,bill);
    }

    @DeleteMapping("{id}")
    public void deleteBill(@PathVariable int id){
        restaurantService.removeBill(id);
    }

    @DeleteMapping
    public void deleteAllBills(){
        restaurantService.removeAllBills();
    }






}
