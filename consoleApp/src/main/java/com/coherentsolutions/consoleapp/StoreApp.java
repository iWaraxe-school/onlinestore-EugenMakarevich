package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.categories.Categories;
import com.coherentsolutions.store.RandomProductGenerator;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;

public class StoreApp {

    public static void main(String[] args) {
        Store store = new Store();
        RandomProductGenerator generator = new RandomProductGenerator();
        System.out.println(generator.generateProduct(Categories.PHONES));

        RandomStorePopulator populator = new RandomStorePopulator(store);
        populator.createCategories();
        System.out.println(store.getCategories());
    }
}
