package com.coherentsolutions.store.interfaces;

import com.coherentsolutions.domain.products.Product;
import com.coherentsolutions.store.Store;
import com.coherentsolutions.store.StorePrinter;
import com.coherentsolutions.store.sorts.Comparator;
import com.coherentsolutions.store.sorts.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserCommands {
    StorePrinter storePrinter;
    XMLParser xmlParser;
    Comparator xmlComparator;
    private Store store;

    public UserCommands(Store store) {
        this.store = store;
        storePrinter = new StorePrinter(store);
        xmlParser = new XMLParser();
        xmlComparator = new Comparator();
    }

    //Sort products based on the sort order parsed from XML
    public void sort() throws IOException, ParserConfigurationException, SAXException {
        //Parse XML to get sort order
        Map<String, String> sortOrder = xmlParser.parseXMLToMap("store/src/main/resources/config.xml", "sort");
        List<Product> products = store.getAllProducts();

        //Sort products
        products.sort(xmlComparator.buildComparator(xmlComparator.chooseComparator(sortOrder)));

        storePrinter.printProducts(products);
    }

    //Get the list of top 5 most expensive products and print it in console
    public void top5() {
        List<Product> products = store.getAllProducts();

        //Sort products by price -> desc
        products.sort(java.util.Comparator.comparing(Product::getPrice).reversed());

        //Get top 5 products
        List<Product> top5 = products.stream().limit(5).collect(Collectors.toList());

        storePrinter.printProducts(top5);
    }

    public void quit() {
        System.out.println("Exit the store. Goodbye!");
    }

    public void print() {
        System.out.println(storePrinter.printStore());
    }
}
