package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Categories;
import com.coherentsolutions.domain.categories.Category;
import com.coherentsolutions.domain.categories.CategoryFactory;
import com.coherentsolutions.domain.products.Product;

import java.util.Random;

/**
 * Generates fake data for the Store
 * Creates categories specified in Categories and instantiates them via CategoryFactory
 * Each category is filled with generated products
 */
public class RandomStorePopulator {
    private Store store;
    private RandomProductGenerator generator;
    private CategoryFactory factory;
    final int MAX_NUMBER_PER_CATEGORY = 10;

    public RandomStorePopulator(Store store, CategoryFactory factory) {
        this.store = store;
        this.factory = factory;
        generator = new RandomProductGenerator();
    }

    public void fillStoreRandomly() {
        createCategories();

        for (Category category : store.getCategories()) {
            createProducts(category, new Random().nextInt(MAX_NUMBER_PER_CATEGORY) + 1);
        }
    }

    // use Factory class to manually instantiates the categories
    public void createCategories() {
        for (Categories eCategory : Categories.values()) {
            Category category = factory.getCategory(eCategory);
            store.addCategory(category);
        }
    }

    // generate products and add them to the category
    public void createProducts(Category category, int productNum) {
        for (int i = 0; i < productNum; i++) {
            Product product = generator.generateProduct(category.getCategory());
            category.addProduct(product);
        }
    }
}
