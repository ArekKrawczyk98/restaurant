package com.example.restaurant.api.product;

import com.example.restaurant.domain.product.Product;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProductRepository {
   private ProductSpringData productSpringData;

    public ProductEntity loadById(int id){
        Optional<ProductEntity> optional =productSpringData.findById(id);

        if (optional.isPresent()){
            return optional.get();
        }
        else throw new IllegalStateException("Cannot find it");
    }


    public void delete(String nameToBeDeleted){
        ProductEntity productEntity = productSpringData.findByName(nameToBeDeleted);
      if (productEntity != null){
          productSpringData.delete(productEntity);

      }

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

    public List<Product> getAll() {
        List<ProductEntity> productEntities = productSpringData.findByOrderByIdAsc();
        List<Product> productList = new ArrayList<>();

        for (ProductEntity x: productEntities) {
            productList.add(ProductMapper.fromEntityToDomainModel(x));

        }

        return productList;
    }
    public ProductEntity getByName(String name){


        return productSpringData.findByName(name);
    }

    public Product getByNameDomainModelFromDB(String name){
        return ProductMapper.fromEntityToDomainModel(productSpringData.findByName(name));
    }


    public void deleteById(Integer id) {
        productSpringData.deleteById(id);
    }
}
