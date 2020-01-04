package com.example.easy.api.invoice;

import com.example.easy.domain.invoice.Invoice;
import com.example.easy.domain.invoice.InvoiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {

    private InvoiceRepository repository;


    public InvoiceController(InvoiceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Invoice get(@PathVariable Integer id){
        return repository.load(String.valueOf(id));
    }

    @GetMapping("/all")
    public List<Invoice> getAll(){
        return repository.findAll();
    }


}
