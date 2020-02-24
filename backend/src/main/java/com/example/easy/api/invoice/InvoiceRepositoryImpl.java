package com.example.easy.api.invoice;

import com.example.easy.domain.invoice.Invoice;
import com.example.easy.domain.invoice.InvoiceRepository;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private InvoiceRepositorySpringData invoiceRepositorySpringData;
    @Override
    public void save(Invoice invoice) {
        invoiceRepositorySpringData.save(invoice.getId(),
                invoice.getDate(),
                invoice.getMoneyThatNeedToBePaid(),
                invoice.getMoneyReceived());
    }

    @Override
    public Invoice load(String id) {

        throw new IllegalStateException("Doesnt exist");

    }

    @Override
    public List<Invoice> getAllFromCurrentDate() {
        List<InvoiceEntity> list = invoiceRepositorySpringData.findByCurrentDay();
        return list.stream().map(InvoiceMapper::fromEntityToDomainModel).collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findAll() {
        List<InvoiceEntity> list = invoiceRepositorySpringData.findAll();
        List<Invoice> domainList = new LinkedList<>();
        for (InvoiceEntity x : list) {
            domainList.add(InvoiceMapper.fromEntityToDomainModel(x));
        }
        return domainList;
    }
}
