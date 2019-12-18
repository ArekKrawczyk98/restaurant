package com.example.easy.api.invoice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date date;
    @Column(name = "money")
    private Double moneyThatNeedToBePaid;
}
