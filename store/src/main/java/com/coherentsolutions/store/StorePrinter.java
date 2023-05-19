package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Category;
import com.coherentsolutions.domain.products.Product;

/**
 * Print Store
 */
public class StorePrinter {

    Store store;

    public StorePrinter(Store store) {
        this.store = store;
    }
    //You've also made an excellent start on the StorePrinter class, which is designed to print the Store.
    // However, I would encourage you to think about how you might implement printing sorted data,
    // as well as the top 5 products by price.

    //Do I need to change the way it's printed in console (like to remove Category for top 5 products for example),
    //or I need to only change the order of printed data?

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
}
