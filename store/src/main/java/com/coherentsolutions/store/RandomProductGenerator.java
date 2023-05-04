package com.coherentsolutions.store;

import com.coherentsolutions.domain.products.Product;
import com.github.javafaker.Faker;

public class RandomProductGenerator {
    Faker faker = new Faker();

    public Product generateProduct() {
        Product product = new Product();
        String name = generateName();
        double rate = generateRate();
        double price = generatePrice();
        product.setName(name);
        product.setRate(rate);
        product.setPrice(price);
        return product;
    }

    private String generateName() {
        if (true) {
            return faker.book().title();
        }
        return "";
    }

    private double generateRate() {
        return faker.number().randomDouble(1, 1, 10);
    }

    private double generatePrice() {
        return faker.number().randomDouble(2, 3, 30);
    }
}
