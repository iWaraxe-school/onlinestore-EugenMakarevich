package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.categories.Category;
import com.coherentsolutions.domain.categories.CategoryFactory;
import com.coherentsolutions.domain.products.Product;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StorePrinter;
import com.coherentsolutions.store.sorts.SortByName;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StoreApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Store store = new Store();
        CategoryFactory factory = new CategoryFactory();

        RandomStorePopulator populator = new RandomStorePopulator(store, factory);
        populator.fillStoreRandomly();

        StorePrinter storePrinter = new StorePrinter(store);
        System.out.println(storePrinter.printStore());

        List<Product> products = getAllProducts(store);
        System.out.println("Unsorted:");
        for (Product product : products) {
            System.out.println(product);
        }

        //Sort by name
        Collections.sort(products, new SortByName());
        System.out.println("Sorted by name:");
        for (Product product : products) {
            System.out.println(product);
        }

        //Compare by name, price and rate
        Comparator<Product> compareByAllFields = Comparator
                .comparing(Product::getName)
                .thenComparing(Product::getPrice)
                .thenComparing(Product::getRate);
        products.sort(compareByAllFields);

        System.out.println("Sorted by all fields:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    // Put the method to the Category or?
    private static List<Product> getAllProducts(Store store) {
        List<Product> products = new ArrayList<>();
        for (Category category : store.getCategories()) {
            products.addAll(category.getProducts());
        }
        return products;
    }
}
