package com.example.easy.domain.order;

import com.example.easy.domain.product.Product;
import com.example.easy.domain.product.ProductCategory;
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

    private final String id;
    private final List<Product> products;
    @Setter
    private OrderStatus status;

    public Double priceOfAllProducts(){
        double price = 0;
        for (Product x : products) {
            price=+x.getCost();
        }
        return price;
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


