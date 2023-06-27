package com.coherentsolutions.store.datageneration;

import com.coherentsolutions.store.db.DBHelper;
import com.github.javafaker.Faker;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class RandomProductGeneratorDb extends DBHelper {
    final int MAX_PRODUCTS_PER_CATEGORY = 10;
    Faker faker = new Faker();
    private int productNum = new Random().nextInt(MAX_PRODUCTS_PER_CATEGORY) + 1;

    public void generateProduct(int category_id, String categoryName) throws SQLException {
        String query = "INSERT INTO products (category_id, name, price, rate) VALUES ("
                + category_id + ",\""
                + generateName(categoryName) + "\","
                + generatePrice() + ","
                + generateRate() + ");";
        //Put this method to the DBhelper
        PreparedStatement ps = getConn().prepareStatement(query);
        ps.executeUpdate();
    }

    private String generateName(String categoryName) {
        switch (categoryName) {
            case "BOOKS":
                return faker.book().title();
            case "FOOD":
                return faker.food().dish();
            case "PHONES":
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
