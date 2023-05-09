package com.coherentsolutions.domain.categories.subcategories;

import com.coherentsolutions.domain.categories.Category;
import com.coherentsolutions.domain.products.Product;

import java.util.List;

public class PhoneCategory extends Category {
    public PhoneCategory(String name, List<Product> products) {
        super(name, products);
    }
}
