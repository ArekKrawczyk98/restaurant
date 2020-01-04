package com.example.easy.api.invoice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "invoices")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date date;
    @Column(name = "money_paid")
    private Double moneyThatNeedToBePaid;
    @Column(name = "money_given")
    private Double moneyGiven;
}
