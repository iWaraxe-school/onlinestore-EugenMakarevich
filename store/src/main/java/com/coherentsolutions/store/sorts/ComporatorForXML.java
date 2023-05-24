package com.coherentsolutions.store.sorts;

import com.coherentsolutions.domain.products.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ComporatorForXML {
    //Create a list of
    public List<Comparator<Product>> allComporators() {
        List<Comparator<Product>> comparators = new ArrayList<>();
        comparators.add(Comparator.comparing(Product::getName));
        comparators.add(Comparator.comparing(Product::getPrice));
        comparators.add(Comparator.comparing(Product::getRate));
        return comparators;
    }

    //Create a new List of Comporators and add only sorts that corresponds to the map values
    public List<Comparator<Product>> chooseComporator(Map<String, String> sortOrder) {
        List<Comparator<Product>> allComparators = allComporators();
        List<Comparator<Product>> sortList = new ArrayList<>();

        //Iterate a map and get the key and value
        for (Map.Entry<String, String> entry : sortOrder.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            switch (entry.getKey()) {
                case "name":
                    if (entry.getValue().equalsIgnoreCase("ASC")) {
                        sortList.add(allComparators.get(0));
                    } else {
                        sortList.add(allComparators.get(0).reversed());
                    }
                    break;
                case "price":
                    if (entry.getValue().equalsIgnoreCase("ASC")) {
                        sortList.add(allComparators.get(1));
                    } else {
                        sortList.add(allComparators.get(1).reversed());
                    }
                    break;
                case "rate":
                    if (entry.getValue().equalsIgnoreCase("ASC")) {
                        sortList.add(allComparators.get(2));
                    } else {
                        sortList.add(allComparators.get(2).reversed());
                    }
                    break;
                default:
                    System.out.println("This is the default value");
            }
        }
        return sortList;
    }

    //Need to create a method that create one general comparator out of the list of comparators
    public Comparator<Product> buildComparator(List<Comparator<Product>> comparators) {
        return comparators.get(0).thenComparing(comparators.get(1)).thenComparing(comparators.get(2));
    }
}
