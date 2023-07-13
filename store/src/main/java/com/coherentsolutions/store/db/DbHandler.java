package com.coherentsolutions.store.db;

import com.coherentsolutions.domain.products.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.coherentsolutions.store.db.DBConstants.GET_RANDOM_PRODUCT;
import static com.coherentsolutions.store.db.DBConstants.INSERT_INTO_ORDERS;

public class DbHandler {
    private static Connection conn;

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

    public static void addOrderToDb(Product order) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_INTO_ORDERS);
        ps.setString(1, order.getName());
        ps.setDouble(2, order.getPrice());
        ps.setDouble(3, order.getRate());
        ps.executeUpdate();
    }
}
