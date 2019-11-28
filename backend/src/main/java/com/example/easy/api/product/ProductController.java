package com.example.easy.api.product;

import com.example.easy.domain.product.Product;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductController {

    private ProductRepository repository;

    public ProductController(ProductRepository productRepository){
        this.repository= productRepository;
    }


    @GetMapping("/all")
    public List<Product> getAllProducts(){

        return repository.getAll();

    }
}
