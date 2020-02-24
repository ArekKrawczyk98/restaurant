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

    private final BillRepository billRepository = new BillRepositoryInMemoryImpl();
    private final InvoiceRepository invoiceRepository = new InvoiceRepositoryInMemoryImpl();
    private final OrderRepository orderRepository = new OrderRepositoryInMemoryImpl();
    private final KitchenService kitchenService = new KitchenService(new ArrayList<>(),0.0);
    private final BarService barService = new BarService(new ArrayList<>(),0.0);
    private final RestaurantService restaurantService = new RestaurantService(billRepository,invoiceRepository,orderRepository,kitchenService,barService);

    @Test
    public void shouldAddOrder(){

        Integer table = 1;

        GuestBill guestBill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product(1,"Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, guestBill);

        Assert.assertEquals(25.0,guestBill.getToPay(),0);


    }
    @Test
    public void shouldPayTheBillAsGuest(){
        Integer table = 1;

        GuestBill guestBill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product(1,"Dinner nr 1",25.0,ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, guestBill);

        Double rest=restaurantService.payTheBillAndGetRest(guestBill,40.0);


        Assert.assertEquals(15.0,rest,0);


    }

    @Test
    public void shouldPayTheBillAsOwner(){
        Integer table = 1;
        GuestBill guestBill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),table, GuestPosition.OWNER);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product(1,"Dinner nr 1",25.0,ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, guestBill);

        Double rest=restaurantService.payTheBillAndGetRest(guestBill,0.25);


        Assert.assertEquals(0,rest,0);


    }

    @Test
    public void shouldPayTheBillAsEmployee(){

        Integer table = 1;
        GuestBill guestBill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),table, GuestPosition.EMPLOYEE);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product(1,"Dinner nr 1",25.0,ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, guestBill);

        Double rest=restaurantService.payTheBillAndGetRest(guestBill,15.0);


        Assert.assertEquals(2.5,rest,0);

    }

    @Test
    public void shouldRemoveOrderWhenAlreadyAdded(){
        Integer table = 1;
        GuestBill guestBill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);

        final String orderId = UUID.randomUUID().toString();


        final Order newOrder = new Order(orderId,Collections.singletonList(new Product(1,"Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));

        restaurantService.addOrder(newOrder, guestBill);

        //when

        restaurantService.removeOrderWhenAlreadyAdded(newOrder.getId(), guestBill);



        //then

        Assert.assertEquals(0,guestBill.getToPay(),0);


    }
    @Test
    public void shouldSendOrdersToBar(){
        Integer table = 1;
        GuestBill guestBill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product(1,"Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));

        List<Product>  barProducts= newOrder.returnBarProducts();

        restaurantService.addOrder(newOrder, guestBill);

        Assert.assertEquals(barProducts,barService.getBarOrders());




    }
    @Test
    public void shouldSendOrdersToKitchen(){
        Integer table = 1;
        GuestBill guestBill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);


        final Order newOrder = new Order(UUID.randomUUID().toString(),Collections.singletonList(new Product(1,"Dinner nr 1",25.0, ProductCategory.MAIN_COURSES)));

        List<Product>  kitchenProducts= newOrder.returnKitchenProducts();

        restaurantService.addOrder(newOrder, guestBill);

        Assert.assertEquals(kitchenProducts,kitchenService.getKitchenOrders());



    }

    @Test
    public void shouldAddBill(){

        final GuestBill bill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),1, GuestPosition.GUEST);

        billRepository.add(bill);

        Bill billAdded = billRepository.loadById(1L);

        Assert.assertEquals(bill.getTable(),billAdded.getTable());
    }



    @Test
    public void shouldDeleteBill(){
        Integer table = 1;
        final GuestBill guestBill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);

        restaurantService.addBill(guestBill);

        billRepository.delete(1L);

        Assert.assertNull(billRepository.loadById(1L));

    }

    @Test
    public void shouldUpdateBill(){

        Integer table = 2;
        final GuestBill guestBill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),table, GuestPosition.GUEST);

        restaurantService.addBill(guestBill);


        final GuestBill guestBillUpdated = GuestBill.from(1L,"Mr Adams",15.0,new Date(),3, GuestPosition.OWNER);

       restaurantService.updateBill(1,guestBillUpdated);

       Bill bill = billRepository.loadById(1);



       Assert.assertEquals(15.0,bill.getToPay(),0);



    }

    @Test
    public void shouldGetIdByTableNumber(){
        final GuestBill guestBill = GuestBill.from(1L,"Dr Sara",0.0,new Date(),2, GuestPosition.GUEST);
        restaurantService.addBill(guestBill);


        final Long index = billRepository.getIdByTableNumber(guestBill.getTable());


        Assert.assertEquals(1L,index.longValue());

    }

    @Test
    public void shouldGetRightPricesForOrder(){
        List<Product> list = Arrays.asList(
                new Product(1,"name1",25.0,ProductCategory.COLD_DRINKS),
                new Product(2,"name2",30.0,ProductCategory.MAIN_COURSES));
        final Order order = new Order("1",list);

        Bill bill = new Bill(1L,0.0,new Date(),5);

        restaurantService.addBill(bill);
        restaurantService.addOrder(order,bill);

        Bill billLoaded = billRepository.loadById(1L);


        double price= billLoaded.getToPay();

        Assert.assertEquals(55.0,price,0);

    }








}
