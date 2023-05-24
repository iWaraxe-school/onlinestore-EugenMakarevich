package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.categories.CategoryFactory;
import com.coherentsolutions.domain.products.Product;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StorePrinter;
import com.coherentsolutions.store.sorts.ComporatorForXML;
import com.coherentsolutions.store.sorts.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StoreApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Store store = new Store();
        CategoryFactory factory = new CategoryFactory();

        RandomStorePopulator populator = new RandomStorePopulator(store, factory);
        populator.fillStoreRandomly();

        StorePrinter storePrinter = new StorePrinter(store);
        System.out.println(storePrinter.printStore());

        List<Product> products = store.getAllProducts();
        System.out.println("Unsorted:");
        for (Product product : products) {
            System.out.println(product);
        }

        /*//Compare by name, price and rate
        Comparator<Product> compareByAllFields = Comparator
                .comparing(Product::getName)
                .thenComparing(Product::getPrice)
                .thenComparing(Product::getRate);
        products.sort(compareByAllFields);

        System.out.println("Sorted by all fields:");
        for (Product product : products) {
            System.out.println(product);
        }*/

        //Parse XML to get sort order
        //TODO: Create a list of products with equal values to check if sort works correctly

        List<Product> productsEqual = new ArrayList<>();
        productsEqual.add(new Product("Phone", 199.99, 7.88));
        productsEqual.add(new Product("Phone", 129.35, 5.70));
        productsEqual.add(new Product("APhone", 1200.95, 9.99));
        productsEqual.add(new Product("APhone", 1300.95, 9.99));
        productsEqual.add(new Product("APhone", 1500.95, 9.99));
        productsEqual.add(new Product("IPhone", 1500.99, 9.99));
        productsEqual.add(new Product("IPhone", 1500.99, 9.98));
        productsEqual.add(new Product("IPhone", 1500.99, 9.97));

        XMLParser xmlParser = new XMLParser();
        Map<String, String> sortOrder = xmlParser.parseXMLToMap("store/src/main/resources/config.xml", "sort");
        System.out.println(sortOrder);
        System.out.println();


        ComporatorForXML xmlComparator = new ComporatorForXML();
        List<Comparator<Product>> comparators = xmlComparator.chooseComporator(sortOrder);
        productsEqual.sort(xmlComparator.buildComparator(comparators));
        System.out.println("ComporatorForXML");
        for (Product product : productsEqual) {
            System.out.println(product);
        }
    }


}
