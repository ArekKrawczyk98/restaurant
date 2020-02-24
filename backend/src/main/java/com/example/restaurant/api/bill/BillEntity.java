package com.example.restaurant.api.bill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table( name = "bills")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    @Column(name = "money_paid")
    private Double moneyPaid;
    @Column(name = "table_number")
    private Integer tableNumber;
}
