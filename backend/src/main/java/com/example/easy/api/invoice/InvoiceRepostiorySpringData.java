package com.example.easy.api.invoice;

import com.example.easy.domain.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepostiorySpringData extends CrudRepository<Invoice,String> {
}
