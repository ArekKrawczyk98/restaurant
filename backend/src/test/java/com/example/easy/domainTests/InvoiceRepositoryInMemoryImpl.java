package com.example.easy.domainTests;

import com.example.easy.domain.invoice.Invoice;
import com.example.easy.domain.invoice.InvoiceRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public Invoice loadByDate(Date date) {
        //TODO date
        return null;
    }

    @Override
    public List<Invoice> getAllFromCurrentDate() {
        List<Invoice> list = new ArrayList<>();
        List<Invoice> temp = new ArrayList<>(database.values());

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        Date dateFromInvoice;

        for (Invoice invoice: temp) {
        dateFromInvoice = invoice.getDate();
        localDate= Instant.ofEpochMilli(dateFromInvoice.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        int yearFromInvoice = localDate.getYear();
        int monthFromInvoice = localDate.getMonthValue();
        int dayFromInvoice = localDate.getDayOfMonth();

        if (yearFromInvoice==year && monthFromInvoice==month && dayFromInvoice==day){
            list.add(invoice);
        }
        }
        return list;

    }

    @Override
    public List<Invoice> findAll() {
        return (List<Invoice>) database.values();
    }


}
