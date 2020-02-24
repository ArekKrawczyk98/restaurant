package com.example.easy.domain;

import com.example.easy.domain.guest.BillRepository;
import com.example.easy.domain.invoice.Invoice;
import com.example.easy.domain.invoice.InvoiceRepository;
import com.example.easy.domain.kitchen.KitchenService;
import com.example.easy.domain.order.Order;
import com.example.easy.domain.order.OrderRepository;
import com.example.easy.domain.order.OrderStatus;
import com.example.easy.domain.product.Product;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
public class RestaurantService {

    private final BillRepository billRepository;
    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final KitchenService kitchenService;
    private final BarService barService;






    public Double payTheBillAndGetRest(Bill bill, Double moneyGiven){

        Double moneyNeeded  = bill.billToBePaid();


        if (moneyGiven<moneyNeeded){
            throw new IllegalStateException("There is no enough funds to pay the bill");
        }

        else{

            Invoice invoice = new Invoice(UUID.randomUUID().toString(),new Date(),moneyNeeded,moneyGiven);
            invoiceRepository.save(invoice);
            this.removeBill(bill.getId());
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
        orderRepository.add(order, Math.toIntExact(bill.getId()));
        bill.addToPay(order);
        billRepository.update(bill.getId(),bill);
        sendOrders(order);

    }



    public void removeOrderWhenAlreadyAdded(String id, Bill bill) {

        Order order = orderRepository.load(id);

        Double moneyFromOrder = order.priceOfAllProducts();

        bill.subtractMoney(moneyFromOrder);

        order.setStatus(OrderStatus.REMOVED);

        List<Product> list = order.getProducts();

        barService.getBarOrders().removeAll(list);
        kitchenService.getKitchenOrders().removeAll(list);

        orderRepository.update(id,order);

    }

    private void sendOrders(Order order){
        kitchenService.addToOrders(order.returnKitchenProducts());
        barService.addToOrders(order.returnBarProducts());

    }

    public boolean addBill(Bill bill){
        Long id = billRepository.add(bill);
        return id != 0L;

    }



    public Bill getBillById(long id) {
        return billRepository.loadById(id);
    }

    public Integer updateBill(long id, Bill bill) {

        billRepository.update(id,bill);

        return bill.getTable();

    }

    public void removeBill(long id) {
        orderRepository.removeAllOrdersByBillId(id);
        billRepository.delete(id);
    }

    public void removeAllBills() {
        billRepository.deleteAll();
    }

}
