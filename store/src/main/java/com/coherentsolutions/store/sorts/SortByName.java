package com.coherentsolutions.store.sorts;

import com.coherentsolutions.domain.products.Product;

import java.util.Comparator;

public class SortByName implements Comparator<Product> {
    public int compare(Product a, Product b) {
        return a.getName().compareTo(b.getName());
    }
}
