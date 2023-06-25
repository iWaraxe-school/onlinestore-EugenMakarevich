package com.coherentsolutions.store.datageneration;

import com.coherentsolutions.domain.categories.Category;
import com.coherentsolutions.domain.categories.CategoryFactory;
import com.coherentsolutions.domain.products.Product;
import com.coherentsolutions.store.Store;

public class RandomStorePopulatorSQL {
    private Store store;
    private RandomProductGenerator generator;
    private CategoryFactory factory;

    public RandomStorePopulatorSQL(Store store, CategoryFactory factory) {
        this.store = store;
        this.factory = factory;
        generator = new RandomProductGenerator();
    }

    public void fillStoreRandomly() {
        //createCategories();
        int productNum = generator.getRandomProductNum();

        for (Category category : store.getCategories()) {
            createProducts(category, productNum);
        }
    }

    // use Factory class to manually instantiates the categories

    // generate products and add them to the category
    public void createProducts(Category category, int productNum) {
        for (int i = 0; i < productNum; i++) {
            Product product = generator.generateProduct(category.getCategory());
            category.addProduct(product);
        }
    }

    public void insertCategory() {

    }
}
