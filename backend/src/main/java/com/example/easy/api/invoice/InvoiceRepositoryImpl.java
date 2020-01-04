package com.example.easy.api.invoice;

import com.example.easy.domain.invoice.Invoice;
import com.example.easy.domain.invoice.InvoiceRepository;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private InvoiceRepositorySpringData invoiceRepostiorySpringData;
    @Override
    public void save(Invoice invoice) {
        invoiceRepostiorySpringData.save(InvoiceMapper.fromDomainModelToEntity(invoice));
    }

    @Override
    public Invoice load(String id) {

        throw new IllegalStateException("Doesnt exist");

    }

    @Override
    public List<Invoice> getAllFromCurrentDate() {
        return null;
    }

    @Override
    public List<Invoice> findAll() {
        List<InvoiceEntity> list = (List<InvoiceEntity>) invoiceRepostiorySpringData.findAll();
        List<Invoice> domainList = new LinkedList<>();
        for (InvoiceEntity x : list) {
            domainList.add(InvoiceMapper.fromEntityToDomainModel(x));
        }
        return domainList;
    }
}
