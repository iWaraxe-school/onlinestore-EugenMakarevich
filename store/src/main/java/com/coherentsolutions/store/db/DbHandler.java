package com.coherentsolutions.store.db;

import com.coherentsolutions.domain.products.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.coherentsolutions.store.db.DBConstants.GET_RANDOM_PRODUCT;

public class DbHandler {
    private Connection conn;

    public DbHandler() throws SQLException {
        conn = DBConnection.getInstance().getConnection();
    }

    public Product getRandomProduct() throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery(GET_RANDOM_PRODUCT);
        resultSet.next();

        return Product.newBuilder()
                .setName(resultSet.getString("name"))
                .setPrice(resultSet.getDouble("price"))
                .setRate(resultSet.getDouble("rate"))
                .build();
    }
}
