package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.categories.Categories;
import com.coherentsolutions.store.RandomProductGenerator;
import com.coherentsolutions.store.RandomStorePopulator;

public class StoreApp {

    public static void main(String[] args) {
        RandomProductGenerator generator = new RandomProductGenerator();
        System.out.println(generator.generateProduct(Categories.PHONES));

        RandomStorePopulator populator = new RandomStorePopulator();
        populator.createCategories();
    }
}
