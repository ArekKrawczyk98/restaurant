package com.example.restaurant.api.bill;

import com.example.restaurant.domain.Bill;
import com.example.restaurant.domain.RestaurantService;
import com.example.restaurant.domain.guest.BillRepository;
import com.example.restaurant.domain.product.Product;
import com.example.restaurant.domain.tableBill.TableBill;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
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
    public Bill addBill(@RequestBody Bill bill){
      Long id = restaurantService.addBill(bill);
      if (id!= null){
          return new Bill(id    ,bill.getToPay(),bill.getDate(),bill.getTable());
      }
      else {
          return null;
      }

    }

    @PutMapping("/{id}")
    public Integer updateBill(@PathVariable int id,Bill bill){

       return restaurantService.updateBill(id,bill);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable long id){

        restaurantService.removeBill(id);

    }

    @DeleteMapping
    public void deleteAllBills(){
        restaurantService.removeAllBills();
    }

    @PostMapping("/payBill")
    public Double payAndGetRestFromBill(@RequestBody BillDTO billDTO){

        return restaurantService.payTheBillAndGetRest(billDTO.getBill(),billDTO.getMoney());

    }
    @PostMapping("/split")
    public void splitBill(@RequestBody SplitBillDTO splitBillDTO){
        boolean isSuccessful = restaurantService.splitBill(splitBillDTO.getBill(),splitBillDTO.getProducts());
        if(!isSuccessful){
            throw new IllegalStateException("Cannot split the bill");
        }
    }







}
