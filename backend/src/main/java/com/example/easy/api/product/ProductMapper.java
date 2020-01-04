package com.example.easy.api.product;

import com.example.easy.domain.product.Product;
import com.example.easy.domain.product.ProductCategory;

public class ProductMapper {

    public static Product fromEntityToDomainModel(ProductEntity productEntity){
        return new Product(productEntity.getName(),productEntity.getCost(),assignToCategory(productEntity.getName()));
    }
    public static ProductEntity fromDomainModelToEntity(Product product){
        return new ProductEntity(product.getName(),product.getCost());
    }

    private static ProductCategory assignToCategory(String name) {
        if (containsIgnoreCase(name,"SOUP")){
            return ProductCategory.SOUPS;
        }
        else if (name.contains("30ml") || containsIgnoreCase(name,"BEER") || containsIgnoreCase(name,"WINE")){
            return ProductCategory.ALCOHOL;
        }
        else if (containsIgnoreCase(name,"hot") || name.contains("Latte") || name.contains("Espresso")){
            return ProductCategory.HOT_DRINKS;
        }
        else if (containsIgnoreCase(name,"salad")){
            return ProductCategory.SALADS;
        }
        else if (containsIgnoreCase(name,"dessert") ||
                containsIgnoreCase(name,"ice cream") ||
                containsIgnoreCase(name,"cake")){
            return ProductCategory.DESSERTS;
        }
        else if (containsIgnoreCase(name,"ice") || containsIgnoreCase(name,"cold")){
            return ProductCategory.COLD_DRINKS;
        }
        else if (containsIgnoreCase(name,"peanuts") || containsIgnoreCase(name,"breadsticks")){
            return ProductCategory.SNACKS;
        }
        else if (containsIgnoreCase(name,"starter")){
            return ProductCategory.STARTERS;
        }
        else return ProductCategory.MAIN_COURSES;


    }

    private static boolean containsIgnoreCase(String str, String substring) {
        return str.toLowerCase().contains(substring.toLowerCase());
    }
}
