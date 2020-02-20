package com.example.easy.domain.invoice;

import java.util.Date;
import java.util.List;

public interface InvoiceRepository {
    void save(Invoice invoice);
    Invoice load(String id);
    Invoice loadByDate(Date date);
    List<Invoice> getAllFromCurrentDate();

    List<Invoice> findAll();

}
