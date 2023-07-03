package com.coherentsolutions.store.datageneration;

import com.coherentsolutions.store.db.DBConnection;
import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.coherentsolutions.store.db.DBConstants.INSERT_INTO_PRODUCT;

public class RandomProductGeneratorDb {

    Faker faker = new Faker();
    Connection conn;

    public RandomProductGeneratorDb() {
        try {
            conn = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateProduct(int category_id, String categoryName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_INTO_PRODUCT);
        ps.setInt(1, category_id);
        ps.setString(2, generateName(categoryName));
        ps.setDouble(3, generatePrice());
        ps.setDouble(4, generateRate());
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
