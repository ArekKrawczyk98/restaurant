package com.example.easy.domainTests;

import com.example.easy.domain.*;
import com.example.easy.domain.guest.BillRepository;
import com.example.easy.domain.invoice.InvoiceRepository;
import com.example.easy.domain.kitchen.KitchenService;
import com.example.easy.domain.order.Order;
import com.example.easy.domain.order.OrderRepository;
import com.example.easy.domain.product.Product;
import com.example.easy.domain.product.ProductCategory;
import com.example.easy.domain.tableBill.TableBill;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ServiceForTableTest {
    private final Zmiana current = new Zmiana(new Date(),0.0);
    private final BillRepository billRepository = new BillRepositoryInMemoryImpl();
    private final InvoiceRepository invoiceRepository = new InvoiceRepositoryInMemoryImpl();
    private final OrderRepository orderRepository = new OrderRepositoryInMemoryImpl();
    private final KitchenService kitchenService = new KitchenService(new ArrayList<>(),0.0);
    private final BarService barService = new BarService(new ArrayList<>(),0.0);
    private final RestaurantService restaurantService = new RestaurantService(billRepository,invoiceRepository,current,orderRepository,kitchenService,barService);

    @Test
    public void shouldAddOrder(){

        Table table = new Table(1);

        TableBill tableBill = new TableBill(0.0,new Date(),table);


        final Order newOrder = new Order(UUID.randomUUID().toString(), Collections.singletonList(new Product("Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, tableBill);

        assertEquals(tableBill.getToPay(),new Double(25.0));


    }

    @Test
    public void shouldSplitTheBill(){
        Table table = new Table(1);

        TableBill tableBill = new TableBill(0.0,new Date(),table);


        final Order newOrder = new Order(UUID.randomUUID().toString(), Collections.singletonList(new Product("Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));
        final Order newOrder1 = new Order(UUID.randomUUID().toString(), Collections.singletonList(new Product("Dinner nr 2",30.0, ProductCategory.MAIN_COURSES)));


        restaurantService.addOrder(newOrder, tableBill);
        restaurantService.addOrder(newOrder1,tableBill);

        Bill bill = tableBill.splitTableBill(30.0);

        assertEquals(tableBill.getToPay(),new Double(25.0));
        assertEquals(bill.getToPay(),new Double(30.0));

    }



}
