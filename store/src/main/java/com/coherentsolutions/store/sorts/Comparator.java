package com.coherentsolutions.store.sorts;

import com.coherentsolutions.domain.products.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class allows to sort Product objects by their fields: name, price and rate
 * Sort order is retrieved from XML. Sort options are ascending and descending
 */

public class Comparator {

    //List of comparators for Product
    public Map<String, java.util.Comparator<Product>> getComparators() {
        Map<String, java.util.Comparator<Product>> comparators = new HashMap<>();
        comparators.put("name", java.util.Comparator.comparing(Product::getName));
        comparators.put("price", java.util.Comparator.comparing(Product::getPrice));
        comparators.put("rate", java.util.Comparator.comparing(Product::getRate));
        return comparators;
    }

    //Choose only those comparators specified in XML
    public List<java.util.Comparator<Product>> chooseComparator(Map<String, String> sortOrder) {
        Map<String, java.util.Comparator<Product>> comparatorMap = getComparators();
        List<java.util.Comparator<Product>> sortList = new ArrayList<>();

        for (Map.Entry<String, String> entry : sortOrder.entrySet()) {
            java.util.Comparator<Product> comparator = comparatorMap.get(entry.getKey());
            if (comparator != null) {
                if (entry.getValue().equalsIgnoreCase("DESC")) {
                    comparator = comparator.reversed();
                }
                sortList.add(comparator);
            } else {
                System.out.println("Unknown sort field: " + entry.getKey());
            }
        }
        return sortList;
    }

    //Join separate comparators to the final comparator via thenComparing
    public java.util.Comparator<Product> buildComparator(List<java.util.Comparator<Product>> comparators) {
        if (comparators.isEmpty()) {
            throw new IllegalArgumentException("No comparators provided");
        }

        java.util.Comparator<Product> compositeComparator = comparators.get(0);

        for (int i = 1; i < comparators.size(); i++) {
            compositeComparator = compositeComparator.thenComparing(comparators.get(i));
        }
        return compositeComparator;
    }
}
