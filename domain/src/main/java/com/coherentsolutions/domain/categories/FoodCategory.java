package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.products.Product;

import java.util.List;

public class FoodCategory extends Category {
    public FoodCategory(String name, List<Product> products) {
        super(name, products);
    }
}
