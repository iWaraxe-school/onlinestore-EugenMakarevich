package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Category;
import com.coherentsolutions.domain.products.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    private static volatile Store store;
    private List<Category> categories = new ArrayList<>();

    private Store() {
    }

    //Apply Thread-safe Singleton pattern with lazy loading
    public static Store getStore() {
        Store result = store;
        if (result != null) {
            return result;
        }
        synchronized (Store.class) {
            if (store == null) {
                store = new Store();
            }
            return store;
        }
    }

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
