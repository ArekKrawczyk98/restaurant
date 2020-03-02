package com.example.restaurant.domainTests;

import com.example.restaurant.domain.BarService;
import com.example.restaurant.domain.Bill;
import com.example.restaurant.domain.RestaurantService;
import com.example.restaurant.domain.guest.BillRepository;
import com.example.restaurant.domain.invoice.InvoiceRepository;
import com.example.restaurant.domain.kitchen.KitchenService;
import com.example.restaurant.domain.order.Order;
import com.example.restaurant.domain.order.OrderRepository;
import com.example.restaurant.domain.product.Product;
import com.example.restaurant.domain.product.ProductCategory;
import com.example.restaurant.domain.tableBill.TableBill;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ServiceForTableTest {
    private final BillRepository billRepository = new BillRepositoryInMemoryImpl();
    private final InvoiceRepository invoiceRepository = new InvoiceRepositoryInMemoryImpl();
    private final OrderRepository orderRepository = new OrderRepositoryInMemoryImpl();
    private final KitchenService kitchenService = new KitchenService(new ArrayList<>(),0.0);
    private final BarService barService = new BarService(new ArrayList<>(),0.0);
    private final RestaurantService restaurantService = new RestaurantService(billRepository,invoiceRepository,orderRepository,kitchenService,barService);

    @Test
    public void shouldAddOrder(){

        Integer table = 1;

        TableBill tableBill = new TableBill(1L,0.0,new Date(),table);


        final Order newOrder = new Order(UUID.randomUUID().toString(), Collections.singletonList(new Product(125,"Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, tableBill);

        assertEquals(tableBill.getToPay(),new Double(25.0));


    }

    @Test
    public void shouldSplitTheBill(){
        Integer table = 1;

        TableBill tableBill = new TableBill(1L,0.0,new Date(),table);


        final Order newOrder = new Order(UUID.randomUUID().toString(), Collections.singletonList(new Product(1,"Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));
        final Order newOrder1 = new Order(UUID.randomUUID().toString(), Collections.singletonList(new Product(1,"Dinner nr 2",30.0, ProductCategory.MAIN_COURSES)));


        restaurantService.addOrder(newOrder, tableBill);
        restaurantService.addOrder(newOrder1,tableBill);

        Bill bill = tableBill.splitTableBill(newOrder1.getProducts().stream().mapToDouble(Product::getCost).sum());

        assertEquals(tableBill.getToPay(),new Double(25.0));
        assertEquals(bill.getToPay(),new Double(30.0));

    }



}
