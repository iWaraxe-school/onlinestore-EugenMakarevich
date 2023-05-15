package com.coherentsolutions.store;

import com.coherentsolutions.domain.categories.Categories;
import com.coherentsolutions.domain.products.Product;
import com.github.javafaker.Faker;

/**
 * Generates products with name, rate and price
 * Names are generated based on the category
 */
public class RandomProductGenerator {
    Faker faker = new Faker();

    public Product generateProduct(Categories categoryName) {
        return new Product(
                generateName(categoryName),
                generateRate(),
                generatePrice()
        );
    }

    private String generateName(Categories categoryName) {
        switch (categoryName) {
            case BOOKS:
                return faker.book().title();
            case FOOD:
                return faker.food().dish();
            case PHONES:
                return faker.commerce().productName();
            default:
                throw new IllegalArgumentException();
        }
    }

    private double generateRate() {
        return faker.number().randomDouble(1, 1, 10);
    }

    private double generatePrice() {
        return faker.number().randomDouble(2, 3, 30);
    }
}
