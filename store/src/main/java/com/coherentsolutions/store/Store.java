package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Category;
import com.coherentsolutions.domain.products.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        for (Category category : getCategories()) {
            products.addAll(category.getProducts());
        }
        return products;
    }

    @Override
    public String toString() {
        return "Store{" +
                "categories=" + categories +
                '}';
    }
}
