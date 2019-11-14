package com.example.easy.domainTests;

import com.example.easy.domain.invoice.Invoice;
import com.example.easy.domain.invoice.InvoiceRepository;

import java.util.HashMap;

public class InvoiceRepositoryInMemoryImpl implements InvoiceRepository {
    private final HashMap<String, Invoice> database = new HashMap<>();

    @Override
    public void save(Invoice invoice) {
        database.put(invoice.getId(),invoice);

    }

    @Override
    public Invoice load(String id) {
        return database.get(id);
    }


}
