package com.example.easy.api.bill;

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
    @Column(name ="payday")
    private Date date;
    @Column(name = "money_paid")
    private Double moneyPaid;
}
