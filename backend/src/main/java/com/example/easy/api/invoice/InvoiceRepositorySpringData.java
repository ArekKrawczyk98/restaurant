package com.example.easy.api.invoice;

import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepositorySpringData extends CrudRepository<InvoiceEntity,String> {
}
