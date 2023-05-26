package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Category;
import com.coherentsolutions.domain.products.Product;

import java.util.List;

/**
 * Print Store
 */
public class StorePrinter {

    Store store;

    public StorePrinter(Store store) {
        this.store = store;
    }

    public String printStore() {
        StringBuilder storeBuilder = new StringBuilder();
        storeBuilder.append("Store:\n");

        for (Category category : store.getCategories()) {
            storeBuilder.append("Category: ").append(category.getCategory()).append("\n");

            for (Product product : category.getProducts()) {
                storeBuilder.append("- ")
                        .append(String.format("Name: %s, Rate: %.2f, Price: %.2f", product.getName(), product.getRate(), product.getPrice()))
                        .append("\n");
            }
        }
        return storeBuilder.toString();
    }

    public void printProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
