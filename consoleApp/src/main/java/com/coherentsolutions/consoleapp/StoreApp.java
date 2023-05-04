package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.categories.Categories;
import com.coherentsolutions.store.RandomProductGenerator;

public class StoreApp {

    public static void main(String[] args) {
        RandomProductGenerator generator = new RandomProductGenerator(Categories.BOOKS);
        System.out.println(generator.generateProduct(Categories.BOOKS));
    }
}
