package com.example.restaurant.api.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "invoices")
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntity {
    @Id
    private String id;
    private Date date;
    @Column(name = "money_paid")
    private Double moneyThatNeedToBePaid;
    @Column(name = "money_given")
    private Double moneyGiven;
}
