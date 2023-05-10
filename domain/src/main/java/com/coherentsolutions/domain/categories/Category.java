package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;

    public Category(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }
    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
