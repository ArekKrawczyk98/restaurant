package com.example.easy.api.bill;

import com.example.easy.domain.Bill;
import com.example.easy.domain.RestaurantService;
import com.example.easy.domain.Table;
import com.example.easy.domain.guest.BillRepository;
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
    public BillEntity addBill(@RequestBody Bill bill){
        restaurantService.addBill(bill);

        return BillMapper.mapFromDomainModelToEntity(billRepository.loadById(billRepository.getIdByTableNumber(bill.getTable())));
    }

    @PutMapping("/{id}")
    public Integer updateBill(@PathVariable int id,Bill bill){

       return restaurantService.updateBill(id,bill);
    }

    @DeleteMapping("/{tableNumber}")
    public void deleteBill(@PathVariable int tableNumber){

        Integer id = billRepository.getIdByTableNumber(new Table(tableNumber));

        restaurantService.removeBill(id);

    }

    @DeleteMapping
    public void deleteAllBills(){
        restaurantService.removeAllBills();
    }

    @PostMapping("/payBill")
    public Double payAndGetRestFromBill(@RequestBody BillDTO billDTO){

        System.out.println(billDTO.toString());


       /* return restaurantService.payTheBillAndGetRest(billDTO.getBill(),
                billDTO.getMoney());*/

       return null;
    }






}
