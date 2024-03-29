package com.coherentsolutions.store.datageneration;

import com.coherentsolutions.domain.categories.Categories;
import com.coherentsolutions.domain.products.Product;
import com.github.javafaker.Faker;

import java.util.Random;

/**
 * Generates products with name, rate and price
 * Names are generated based on the category
 */
public class RandomProductGenerator {
    Faker faker = new Faker();
    final int MAX_PRODUCTS_PER_CATEGORY = 10;
    private int productNum = new Random().nextInt(MAX_PRODUCTS_PER_CATEGORY) + 1;

    public Product generateProduct(Categories categoryName) {
        return Product.newBuilder()
                .setName(generateName(categoryName))
                .setPrice(generatePrice())
                .setRate(generateRate())
                .build();
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

    public int getRandomProductNum() {
        return productNum;
    }
}
