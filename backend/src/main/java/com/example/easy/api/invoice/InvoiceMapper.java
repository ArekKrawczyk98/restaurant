package com.example.easy.api.invoice;

import com.example.easy.domain.invoice.Invoice;

public class InvoiceMapper {
    public static InvoiceEntity fromDomainModelToEntity(Invoice invoice) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setDate(invoice.getDate());
        invoiceEntity.setId(invoice.getId());
        invoiceEntity.setMoneyThatNeedToBePaid(invoice.getMoneyThatNeedToBePaid());
        return invoiceEntity;
    }
    public static Invoice fromEntityToDomainModel(InvoiceEntity invoiceEntity){
        return new Invoice(invoiceEntity.getId(),
                invoiceEntity.getDate(),
                invoiceEntity.getMoneyThatNeedToBePaid(),
                invoiceEntity.getMoneyGiven());
    }

}
