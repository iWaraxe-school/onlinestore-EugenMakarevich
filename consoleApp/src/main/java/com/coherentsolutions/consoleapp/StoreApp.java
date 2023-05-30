package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.categories.CategoryFactory;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.UserCommands;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class StoreApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Store store = new Store();

        //Fill store with categories and random products
        CategoryFactory factory = new CategoryFactory();
        RandomStorePopulator populator = new RandomStorePopulator(store, factory);
        populator.fillStoreRandomly();

        //Print store
        UserCommands commands = new UserCommands(store);
        commands.print();

        //Sort store and print results
        commands.sort();
        System.out.println();

        //Sort top 5 products by price (desc) and print results
        commands.top5();
    }
}
