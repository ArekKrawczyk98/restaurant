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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name ="payday")
    private Date date;
    private Double moneyPaid;
}
