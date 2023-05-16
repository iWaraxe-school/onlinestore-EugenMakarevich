package com.coherentsolutions.domain.categories;

import com.coherentsolutions.domain.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Categories category;
    private List<Product> products;

    public Category(Categories category) {
        this.category = category;
        this.products = new ArrayList<>();
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProduct(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    @Override
    public String toString() {
        return "Category{" +
                "category=" + category +
                ", products=" + products +
                '}';
    }
}
