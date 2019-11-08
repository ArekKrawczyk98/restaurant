package com.example.easy.api.product;

import com.example.easy.domain.product.Product;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ProductRepository {
    ProductSpringData productSpringData;

    public ProductEntity loadById(int id){
        Optional<ProductEntity> optional =productSpringData.findById(id);

        if (optional.isPresent()){
            return optional.get();
        }
        else throw new IllegalStateException("Cannot find it");
    }


    public void delete(int id){
        productSpringData.deleteById(id);
    }

    public void add(Product product){
        ProductEntity productEntity= new ProductEntity();
        productEntity.setCost(product.getCost());
        productEntity.setName(product.getName());
        productSpringData.save(productEntity);
    }


    public void updateCost(Product product){

        ProductEntity productEntity = productSpringData.findByName(product.getName());
        productEntity.setCost(product.getCost());

        productSpringData.save(productEntity);

    }

}
