package com.example.easy.domainTests;

import com.example.easy.domain.invoice.Invoice;
import com.example.easy.domain.invoice.InvoiceRepository;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.CoreMatchers.instanceOf;

public class InvoiceTest {

    private InvoiceRepository invoiceRepository = new InvoiceRepositoryInMemoryImpl();

    @Test
    public void shouldAddInvoice(){
        String id=UUID.randomUUID().toString();
        Invoice invoice = new Invoice(id,new Date(),50.0,60.0);
        invoiceRepository.save(invoice);

        Invoice saved=invoiceRepository.load(id);

        Assert.assertEquals(id,saved.getId());
    }

    @Test
    public void shouldGetInvoicesFromCurrentDay(){
        List<Invoice> invoiceListFromCurrentDay = new ArrayList<>();

        LocalDate localDate = LocalDate.of(2015,11,30);

        Date oddDate = java.sql.Date.valueOf(localDate);

        Invoice invoice = new Invoice(UUID.randomUUID().toString(),new Date(),60.0,60.0);
        Invoice invoice1 = new Invoice(UUID.randomUUID().toString(),new Date(),25.0,60.0);
        Invoice invoice2 = new Invoice(UUID.randomUUID().toString(),new Date(),6.0,6.0);
        Invoice invoice3 = new Invoice(UUID.randomUUID().toString(),new Date(),95.0,100.0);
        Invoice invoice4 = new Invoice(UUID.randomUUID().toString(),new Date(),60.0,60.0);
        Invoice invoice5 = new Invoice(UUID.randomUUID().toString(),oddDate,11.0,200.0);


        invoiceRepository.save(invoice);
        invoiceRepository.save(invoice1);
        invoiceRepository.save(invoice2);
        invoiceRepository.save(invoice3);
        invoiceRepository.save(invoice4);
        invoiceRepository.save(invoice5);

        invoiceListFromCurrentDay.add(invoice);
        invoiceListFromCurrentDay.add(invoice1);
        invoiceListFromCurrentDay.add(invoice2);
        invoiceListFromCurrentDay.add(invoice3);
        invoiceListFromCurrentDay.add(invoice4);



        List<Invoice> listFromRepo = invoiceRepository.getAllFromCurrentDate();

        boolean allContains = Boolean.FALSE;

        for (Invoice x:listFromRepo) {
             allContains=invoiceListFromCurrentDay.contains(x);

        }

        Assert.assertTrue(allContains);

    }


    @Test
    public void isDateFromRepDateClass(){

        Invoice invoice = new Invoice(UUID.randomUUID().toString(),new Date(),50.0,50.0);
        invoiceRepository.save(invoice);

        Assert.assertThat(invoice.getDate(),instanceOf(Date.class));
    }





}
