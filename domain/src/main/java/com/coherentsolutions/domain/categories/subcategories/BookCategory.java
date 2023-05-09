package com.coherentsolutions.domain.categories.subcategories;

import com.coherentsolutions.domain.categories.Category;
import com.coherentsolutions.domain.products.Product;

import java.util.List;

public class BookCategory extends Category {

    public BookCategory(String name, List<Product> products) {
        super(name, products);
    }
}
