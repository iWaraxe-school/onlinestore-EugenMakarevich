package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.products.Product;

import java.util.List;

public class BookCategory extends Category {

    public BookCategory(String name, List<Product> products) {
        super(name, products);
    }
}
