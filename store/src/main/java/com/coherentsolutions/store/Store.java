package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Category;

import java.util.List;

public class Store {
    private List<Category> categories;

    public void addCategory() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategoryByName(String categoryName, List<Category> categories) {
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Store{" +
                "categories=" + categories +
                '}';
    }
}
