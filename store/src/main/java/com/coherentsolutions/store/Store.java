package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Categories;
import com.coherentsolutions.domain.categories.Category;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategoryByName(Categories categoryName, List<Category> categories) {
        for (Category category : categories) {
            if (category.getCategory().equals(categoryName)) {
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
