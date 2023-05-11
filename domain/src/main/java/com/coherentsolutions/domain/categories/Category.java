package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Categories category;

    public Category(Categories category) {
        this.category = category;
        this.products = new ArrayList<>();
    }

    private List<Product> products;

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category=" + category +
                ", products=" + products +
                '}';
    }
}
