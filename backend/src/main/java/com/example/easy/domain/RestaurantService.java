package com.example.easy.domain;

import com.example.easy.domain.guest.BillRepository;
import com.example.easy.domain.invoice.Invoice;
import com.example.easy.domain.invoice.InvoiceRepository;
import com.example.easy.domain.kitchen.KitchenService;
import com.example.easy.domain.order.Order;
import com.example.easy.domain.order.OrderRepository;
import com.example.easy.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;


@RequiredArgsConstructor
public class RestaurantService {

    private final BillRepository billRepository;
    private final InvoiceRepository invoiceRepository;
    private final Zmiana current;
    private final OrderRepository orderRepository;
    private final KitchenService kitchenService;
    private final BarService barService;





    //Getting the rest
    public Double payTheBill(Bill bill, Double moneyGiven){

        Double moneyNeeded  = bill.billToBePaid();


        if (moneyGiven<moneyNeeded){
            throw new IllegalStateException("There is no enough funds to pay the bill");
        }

        else{

            Invoice invoice = new Invoice(UUID.randomUUID().toString(),new Date(),moneyNeeded,moneyGiven);
            invoiceRepository.save(invoice);
            current.addToCurrentMoney(invoice);
            if(moneyGiven.equals(moneyNeeded)){
                return 0.0;
            }
            else {
                return invoice.getRest();
            }
        }

    }

    public void addOrder(Order order, Bill bill){
        order.setStatus(OrderStatus.ACCEPTED);
        orderRepository.add(order);
        bill.addToPay(order);
        sendOrders(order);

    }



    public void removeOrderWhenAlreadyAdded(String id, Bill bill) {

        Order order = orderRepository.load(id);

        Double moneyFromOrder = order.priceOfAllProducts();

        bill.subtractMoney(moneyFromOrder);

        order.setStatus(OrderStatus.REMOVED);

        orderRepository.update(id,order);

    }

    private void sendOrders(Order order){
        kitchenService.addToOrders(order.returnKitchenProducts());
        barService.addToOrders(order.returnBarProducts());

    }

    public void addBill(Bill bill){
        billRepository.add(bill);
    }


    public Bill getBillById(int id) {
        Bill bill = billRepository.load(id);
        return bill;
    }

    public Integer updateBill(int id, Bill bill) {

        billRepository.update(id,bill);

        return Integer.valueOf(bill.getTable().getNumber());

    }

    public void removeBill(int id) {
        billRepository.delete(id);
    }

    public void removeAllBills() {
        billRepository.deleteAll();
    }
}
