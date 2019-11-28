package com.example.easy.domainTests;

import com.example.easy.domain.*;
import com.example.easy.domain.guest.GuestBill;
import com.example.easy.domain.guest.GuestPosition;
import com.example.easy.domain.guest.BillRepository;
import com.example.easy.domain.invoice.InvoiceRepository;
import com.example.easy.domain.kitchen.KitchenService;
import com.example.easy.domain.order.Order;
import com.example.easy.domain.order.OrderRepository;
import com.example.easy.domain.product.Product;
import com.example.easy.domain.product.ProductCategory;
import org.junit.Assert;
import org.junit.Test;


import java.util.*;

public class ServiceForGuestTest {

    private final Zmiana current = new Zmiana(new Date(),0.0);
    private final BillRepository billRepository = new BillRepositoryInMemoryImpl();
    private final InvoiceRepository invoiceRepository = new InvoiceRepositoryInMemoryImpl();
    private final OrderRepository orderRepository = new OrderRepositoryInMemoryImpl();
    private final KitchenService kitchenService = new KitchenService(new ArrayList<>(),0.0);
    private final BarService barService = new BarService(new ArrayList<>(),0.0);
    private final RestaurantService restaurantService = new RestaurantService(billRepository,invoiceRepository,current,orderRepository,kitchenService,barService);

    @Test
    public void shouldAddOrder(){

        Table table = new Table("1");

        GuestBill guestBill = GuestBill.from("Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product("Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, guestBill);

        Assert.assertEquals(guestBill.getToPay(),new Double(25.0));


    }
    @Test
    public void shouldPayTheBillAsGuest(){
        Table table = new Table("1");

        GuestBill guestBill = GuestBill.from("Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product("Dinner nr 1",25.0,ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, guestBill);

        Double rest=restaurantService.payTheBillAndGetRest(guestBill,40.0);


        Assert.assertEquals(rest,new Double(15.0));


    }

    @Test
    public void shouldPayTheBillAsOwner(){
        Table table = new Table("1");

        GuestBill guestBill = GuestBill.from("Dr Sara",0.0,new Date(),table, GuestPosition.OWNER);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product("Dinner nr 1",25.0,ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, guestBill);

        Double rest=restaurantService.payTheBillAndGetRest(guestBill,0.25);


        Assert.assertEquals(rest,new Double(0));


    }

    @Test
    public void shouldPayTheBillAsEmployee(){

        Table table = new Table("1");

        GuestBill guestBill = GuestBill.from("Dr Sara",0.0,new Date(),table, GuestPosition.EMPLOYEE);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product("Dinner nr 1",25.0,ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, guestBill);

        Double rest=restaurantService.payTheBillAndGetRest(guestBill,15.0);


        Assert.assertEquals(rest,new Double(2.5));

    }

    @Test
    public void shouldRemoveOrderWhenAlreadyAdded(){
        Table table = new Table("1");

        GuestBill guestBill = GuestBill.from("Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);

        final String orderId = UUID.randomUUID().toString();


        final Order newOrder = new Order(orderId,Collections.singletonList(new Product("Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, guestBill);

        //when

        restaurantService.removeOrderWhenAlreadyAdded(newOrder.getId(), guestBill);



        //then

        Assert.assertEquals(guestBill.getToPay(),new Double(0));


    }
    @Test
    public void shouldSendOrdersToBar(){
        Table table = new Table("1");

        GuestBill guestBill = GuestBill.from("Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product("Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));

        List<Product>  barProducts= newOrder.returnBarProducts();

        restaurantService.addOrder(newOrder, guestBill);

        Assert.assertEquals(barProducts,barService.getBarOrders());




    }
    @Test
    public void shouldSendOrdersToKitchen(){
        Table table = new Table("1");

        GuestBill guestBill = GuestBill.from("Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product("Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));

        List<Product>  kitchenProducts= newOrder.returnKitchenProducts();

        restaurantService.addOrder(newOrder, guestBill);

        Assert.assertEquals(kitchenProducts,kitchenService.getKitchenOrders());



    }

    @Test
    public void shouldAddBill(){

        final GuestBill bill = GuestBill.from("Dr Sara",0.0,new Date(),new Table("1"), GuestPosition.GUEST);

        billRepository.add(bill);

        String tableNumber = billRepository.load(1).getTable().getNumber();

        Assert.assertEquals("1",tableNumber);
    }



    @Test
    public void shouldDeleteBill(){
        Table table = new Table("1");

        final GuestBill guestBill = GuestBill.from("Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);

        restaurantService.addBill(guestBill);

        billRepository.delete(1);

        Assert.assertNull(billRepository.load(1));

    }

    @Test
    public void shouldUpdateBill(){

        Table table = new Table("2");

        final GuestBill guestBill = GuestBill.from("Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);

        restaurantService.addBill(guestBill);


        final GuestBill guestBillUpdated = GuestBill.from("Mr Adams",15.0,new Date(),new Table("2"), GuestPosition.OWNER);


        restaurantService.updateBill(2,guestBillUpdated);


        Bill bill = billRepository.load(2);


        Assert.assertEquals(new Double(15.0),bill.getToPay());



    }







}
