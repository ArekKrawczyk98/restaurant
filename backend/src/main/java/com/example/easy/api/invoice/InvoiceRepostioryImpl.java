package com.example.easy.api.invoice;

import com.example.easy.domain.invoice.Invoice;
import com.example.easy.domain.invoice.InvoiceRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class InvoiceRepostioryImpl implements InvoiceRepository {

    private InvoiceRepostiorySpringData invoiceRepostiorySpringData;
    @Override
    public void save(Invoice invoice) {
        invoiceRepostiorySpringData.save(invoice);

    }

    @Override
    public Invoice load(String id) {
        if (invoiceRepostiorySpringData.existsById(id)){
            Optional<Invoice> optional = invoiceRepostiorySpringData.findById(id);
            if (optional.isPresent()){
                return optional.get();
            }

        }

        throw new IllegalStateException("Doesnt exist");

    }
}
