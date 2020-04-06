package com.example.restaurant.api;

import com.example.restaurant.api.bill.BillRepositoryImpl;
import com.example.restaurant.api.bill.BillRepositorySpringData;
import com.example.restaurant.api.invoice.InvoiceRepositoryImpl;
import com.example.restaurant.api.invoice.InvoiceRepositorySpringData;
import com.example.restaurant.api.order.OrderRepositoryImpl;
import com.example.restaurant.api.order.OrderRepositorySpringData;
import com.example.restaurant.api.product.ProductRepository;
import com.example.restaurant.api.product.ProductSpringData;
import com.example.restaurant.domain.BarService;
import com.example.restaurant.domain.RestaurantService;
import com.example.restaurant.domain.guest.BillRepository;
import com.example.restaurant.domain.invoice.InvoiceRepository;
import com.example.restaurant.domain.kitchen.KitchenService;
import com.example.restaurant.domain.order.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;

@Configuration
public class RestaurantConfiguration {

    @Bean
    RestaurantService restaurantService(BillRepository billRepository,
                                        InvoiceRepository invoiceRepository,
                                        OrderRepository orderRepository,
                                        KitchenService kitchenService,
                                        BarService barService){

        return new RestaurantService(billRepository,
                                     invoiceRepository,
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
        return new KitchenService(new ArrayList<>(), (double) 0);
    }

    @Bean
    BarService barService(){
        return new BarService(new ArrayList<>(), (double) 0);
    }

    @Bean
    ProductRepository productRepository(ProductSpringData productSpringData){
        return new ProductRepository(productSpringData);
    }


    }




