package com.coherentsolutions.consoleapp;

import com.coherentsolutions.store.DbStorePrinter;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.datageneration.RandomStorePopulatorDb;
import com.coherentsolutions.store.db.DBConnection;
import com.coherentsolutions.store.db.SetUpTables;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class StoreApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, SQLException {
        Store store = Store.getStore();
        /*//Fill store with categories and random products
        CategoryFactory factory = new CategoryFactory();
        RandomStorePopulator populator = new RandomStorePopulator(store, factory);
        populator.fillStoreRandomly();

        //Read and run commands from the user
        UserInterface userInterface = new UserInterface(store);
        userInterface.readCommands();*/

        /* Online Store with DataBase */

        //Create connection
        DBConnection.getInstance();

        //Set up tables
        SetUpTables setUpTables = new SetUpTables();
        setUpTables.createCategoriesTable();
        setUpTables.createProductsTable();

        //Fill DB tables with random data
        new RandomStorePopulatorDb().fillStoreRandomly();

        //Print store
        System.out.println(new DbStorePrinter().printStore());
    }
}
