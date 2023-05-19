package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.categories.CategoryFactory;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StorePrinter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class StoreApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Store store = new Store();
        CategoryFactory factory = new CategoryFactory();

        RandomStorePopulator populator = new RandomStorePopulator(store, factory);
        populator.fillStoreRandomly();

        StorePrinter storePrinter = new StorePrinter(store);
        System.out.println(storePrinter.printStore());
    }
}
