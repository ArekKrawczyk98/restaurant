package com.example.restaurant.domain.invoice;

import java.util.List;

public interface InvoiceRepository {
    void save(Invoice invoice);
    Invoice load(String id);
    List<Invoice> getAllFromCurrentDate();

    List<Invoice> findAll();

}
