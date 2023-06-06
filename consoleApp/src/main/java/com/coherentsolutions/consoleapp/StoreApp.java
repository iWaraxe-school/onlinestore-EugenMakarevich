package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.categories.CategoryFactory;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StoreCommandHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class StoreApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Store store = Store.getStore();

        //Fill store with categories and random products
        CategoryFactory factory = new CategoryFactory();
        RandomStorePopulator populator = new RandomStorePopulator(store, factory);
        populator.fillStoreRandomly();

        //Read and run commands from the user
        StoreCommandHandler commandHandler = new StoreCommandHandler(store);
        commandHandler.readCommands();
    }
}
