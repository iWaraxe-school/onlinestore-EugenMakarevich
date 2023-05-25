package com.coherentsolutions.consoleapp;

import com.coherentsolutions.domain.categories.CategoryFactory;
import com.coherentsolutions.domain.products.Product;
import com.coherentsolutions.store.RandomStorePopulator;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StorePrinter;
import com.coherentsolutions.store.sorts.ComparatorForXML;
import com.coherentsolutions.store.sorts.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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

        //Parse XML to get sort order
        XMLParser xmlParser = new XMLParser();
        Map<String, String> sortOrder = xmlParser.parseXMLToMap("store/src/main/resources/config.xml", "sort");
        List<Product> products = store.getAllProducts();

        ComparatorForXML xmlComparator = new ComparatorForXML();
        products.sort(xmlComparator.buildComparator(xmlComparator.chooseComparator(sortOrder)));
        System.out.println("Sorted products:");
        storePrinter.printProducts(products);
    }
}
