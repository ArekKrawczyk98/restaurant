package com.example.restaurant.domain.order;

import com.example.restaurant.domain.product.Product;
import com.example.restaurant.domain.product.ProductCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
public class Order {

    private final Long id;
    private final List<Product> products;
    @Setter
    private OrderStatus status;

    public Double priceOfAllProducts(){

      return this.products.stream().mapToDouble(Product::getCost).sum();
    }

    public List<Product> returnKitchenProducts(){
        List<Product> returnList = new ArrayList<>();
        for (Product x : products){
            if (x.getCategory()== ProductCategory.DESSERTS
                    ||x.getCategory()==ProductCategory.MAIN_COURSES
                    ||x.getCategory()==ProductCategory.SALADS
                    ||x.getCategory()==ProductCategory.SOUPS
                    ||x.getCategory()==ProductCategory.STARTERS){
                returnList.add(x);
            }
        }
       return returnList;
    }

    public List<Product> returnBarProducts(){
        List<Product> returnList = new ArrayList<>();
        for (Product x : products){
            if (x.getCategory()== ProductCategory.ALCOHOL
                    ||x.getCategory()==ProductCategory.COLD_DRINKS
                    ||x.getCategory()==ProductCategory.HOT_DRINKS
                    ||x.getCategory()==ProductCategory.SNACKS){
                returnList.add(x);
            }
        }
        return returnList;
    }
}


