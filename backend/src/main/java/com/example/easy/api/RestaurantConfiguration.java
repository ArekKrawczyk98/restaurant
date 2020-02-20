package com.example.easy.api;

import com.example.easy.api.bill.BillRepositoryImpl;
import com.example.easy.api.bill.BillRepositorySpringData;
import com.example.easy.api.invoice.InvoiceRepositoryImpl;
import com.example.easy.api.invoice.InvoiceRepositorySpringData;
import com.example.easy.api.order.OrderRepositoryImpl;
import com.example.easy.api.order.OrderRepositorySpringData;
import com.example.easy.api.product.ProductRepository;
import com.example.easy.api.product.ProductSpringData;
import com.example.easy.domain.BarService;
import com.example.easy.domain.RestaurantService;
import com.example.easy.domain.Zmiana;
import com.example.easy.domain.guest.BillRepository;
import com.example.easy.domain.invoice.InvoiceRepository;
import com.example.easy.domain.kitchen.KitchenService;
import com.example.easy.domain.order.OrderRepository;
import com.example.easy.domain.product.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;

@Configuration
public class RestaurantConfiguration {

    @Bean
    RestaurantService restaurantService(BillRepository billRepository,
                                        InvoiceRepository invoiceRepository,
                                        Zmiana zmiana,
                                        OrderRepository orderRepository,
                                        KitchenService kitchenService,
                                        BarService barService){

        return new RestaurantService(billRepository,
                                     invoiceRepository,
                                     zmiana,
                                     orderRepository,
                                     kitchenService,
                                     barService);
    }

    @Bean
    BillRepository billRepository(BillRepositorySpringData billRepositorySpringData){
        return new BillRepositoryImpl(billRepositorySpringData);
    }

    @Bean
    InvoiceRepository invoiceRepository(InvoiceRepositorySpringData invoiceRepositorySpringData){
        return new InvoiceRepositoryImpl(invoiceRepositorySpringData);
    }
    @Bean
    OrderRepository orderRepository(OrderRepositorySpringData orderRepositorySpringData){
        return new OrderRepositoryImpl(orderRepositorySpringData);
    }

    @Bean
    KitchenService kitchenService(){
        return new KitchenService(new ArrayList<Product>(), (double) 0);
    }

    @Bean
    BarService barService(){
        return new BarService(new ArrayList<Product>(), (double) 0);
    }
    @Bean
    Zmiana zmiana(){
        return new Zmiana(new Date(),(double)0);
    }

    @Bean
    ProductRepository productRepository(ProductSpringData productSpringData){
        return new ProductRepository(productSpringData);
    }



}
