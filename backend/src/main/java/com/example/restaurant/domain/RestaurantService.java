package com.example.restaurant.domain;

import com.example.restaurant.domain.guest.BillRepository;
import com.example.restaurant.domain.invoice.Invoice;
import com.example.restaurant.domain.invoice.InvoiceRepository;
import com.example.restaurant.domain.kitchen.KitchenService;
import com.example.restaurant.domain.order.Order;
import com.example.restaurant.domain.order.OrderRepository;
import com.example.restaurant.domain.order.OrderStatus;
import com.example.restaurant.domain.product.Product;
import com.example.restaurant.domain.tableBill.TableBill;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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
        orderRepository.add(order, bill.getId());
        bill.addToPay(order);
        billRepository.update(bill.getId(),bill);
        sendOrders(order);

    }



    public void removeOrderWhenAlreadyAdded(Long id, Bill bill) {

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

    public Long addBill(Bill bill){
        return billRepository.add(bill);

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

    public boolean endService(){

        return billRepository.loadAll().isEmpty() && orderRepository.loadAll().isEmpty();

    }
    public boolean splitBill(TableBill bill, List<Product> list){

        Double newBillValue = list.stream().mapToDouble(Product::getCost).sum();
        Bill newBill = bill.splitTableBill(newBillValue);
        Long id = billRepository.add(newBill);
        Order order = new Order((long) (orderRepository.loadAll().size()+1),list);
        orderRepository.add(order, id);
        List<Order> orders = orderRepository.getAllOrdersForBillId(bill.getId());
        List<Product> products = new LinkedList<>();
        for (Order x: orders) {
             products.addAll(x.getProducts());

        }
        products.removeAll(list);
        orderRepository.removeAllOrdersByBillId(bill.getId());
        orderRepository.add(new Order((long) (orders.size()+1),products),bill.getId());

        return billRepository.loadById(id) != null;




    }

}
