package com.coherentsolutions.store.sorts;

import com.coherentsolutions.domain.products.Product;

import java.util.*;

/**
 * This class allows to sort Product objects by their fields: name, price and rate
 * Sort order is retrieved from XML. Sort options are ascending and descending
 */

public class ComparatorForXML {

    //List of comparators for Product
    public Map<String, Comparator<Product>> getComparators() {
        Map<String, Comparator<Product>> comparators = new HashMap<>();
        comparators.put("name", Comparator.comparing(Product::getName));
        comparators.put("price", Comparator.comparing(Product::getPrice));
        comparators.put("rate", Comparator.comparing(Product::getRate));
        return comparators;
    }

    //Choose only those comparators that are specified in XML
    public List<Comparator<Product>> chooseComparator(Map<String, String> sortOrder) {
        Map<String, Comparator<Product>> comparatorMap = getComparators();
        List<Comparator<Product>> sortList = new ArrayList<>();

        for (Map.Entry<String, String> entry : sortOrder.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    if (entry.getValue().equalsIgnoreCase("ASC")) {
                        sortList.add(comparatorMap.get("name"));
                    } else {
                        sortList.add(comparatorMap.get("name").reversed());
                    }
                    break;
                case "price":
                    if (entry.getValue().equalsIgnoreCase("ASC")) {
                        sortList.add(comparatorMap.get("price"));
                    } else {
                        sortList.add(comparatorMap.get("price").reversed());
                    }
                    break;
                case "rate":
                    if (entry.getValue().equalsIgnoreCase("ASC")) {
                        sortList.add(comparatorMap.get("rate"));
                    } else {
                        sortList.add(comparatorMap.get("rate").reversed());
                    }
                    break;
                default:
                    return null;
            }
        }
        return sortList;
    }

    //Join separate comparators to the final comparator via thenComparing
    public Comparator<Product> buildComparator(List<Comparator<Product>> comparators) {
        if (comparators.isEmpty()) {
            throw new IllegalArgumentException("No comparators provided");
        }

        Comparator<Product> compositeComparator = comparators.get(0);

        for (int i = 1; i < comparators.size(); i++) {
            compositeComparator = compositeComparator.thenComparing(comparators.get(i));
        }
        return compositeComparator;
    }
}
