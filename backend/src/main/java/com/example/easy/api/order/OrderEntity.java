package com.example.easy.api.order;

import com.example.easy.api.product.ProductEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
@ToString
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToMany(orphanRemoval = true)
    @JoinTable(name = "orders_products", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns ={@JoinColumn(name = "product_id")} )
    private List<ProductEntity> productList;


    @Column(name = "bill_id")
    private Integer billId;

}
