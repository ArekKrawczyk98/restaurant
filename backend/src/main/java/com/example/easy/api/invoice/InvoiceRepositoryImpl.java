package com.example.easy.api.invoice;

import com.example.easy.domain.invoice.Invoice;
import com.example.easy.domain.invoice.InvoiceRepository;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    public Invoice loadByDate(Date date) {
       return InvoiceMapper.fromEntityToDomainModel( invoiceRepositorySpringData.findByDate(date));
    }

    @Override
    public List<Invoice> getAllFromCurrentDate() {
        return null;
    }

    @Override
    public List<Invoice> findAll() {
        List<InvoiceEntity> list = (List<InvoiceEntity>) invoiceRepositorySpringData.findAll();
        List<Invoice> domainList = new LinkedList<>();
        for (InvoiceEntity x : list) {
            domainList.add(InvoiceMapper.fromEntityToDomainModel(x));
        }
        return domainList;
    }
}
