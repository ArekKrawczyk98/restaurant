package com.example.easy.domain.invoice;

public interface InvoiceRepository {
    void save(Invoice invoice);
    Invoice load(String id);
}
