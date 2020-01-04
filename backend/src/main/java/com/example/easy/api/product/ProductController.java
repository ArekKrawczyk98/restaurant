package com.example.easy.api.product;

import com.example.easy.domain.product.Product;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ProductEntity getById(@PathVariable int id){
        return repository.loadById(id);

    }
    @PutMapping("/update")
    public ProductEntity updateProduct(@RequestBody Product product){
        repository.updateCost(product);

        return repository.getByName(product.getName());
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        repository.add(product);

        return repository.getByNameDomainModelFromDB(product.getName());

    }
    @DeleteMapping("/delete")
    public void deleteProduct(@RequestBody String name){
        try {
            JSONObject jsonObject = new JSONObject(name);
           repository.delete(jsonObject.get("name").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
