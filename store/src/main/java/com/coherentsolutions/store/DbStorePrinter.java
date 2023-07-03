package com.coherentsolutions.store;

import com.coherentsolutions.store.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.coherentsolutions.store.db.DBConstants.SELECT_ALL_DATA_FROM_CATEGORIES;

public class DbStorePrinter {
    Connection conn;

    public DbStorePrinter() throws SQLException {
        conn = DBConnection.getInstance().getConnection();
    }

    public String printStore() throws SQLException {
        StringBuilder storeBuilder = new StringBuilder();
        storeBuilder.append("Store:\n");

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_DATA_FROM_CATEGORIES);

            while (rs.next()) {
                storeBuilder.append("Category: ").append(rs.getString("category_name")).append("\n");

                String categoryId = rs.getString("id");
                String q = "SELECT * FROM products where category_id = " + categoryId;

                ResultSet rss = conn.createStatement().executeQuery(q);
                while (rss.next()) {
                    storeBuilder.append("- ")
                            .append(String.format("Name: %s, Rate: %.2f, Price: %.2f", rss.getString("name"), rss.getDouble("price"), rss.getDouble("rate")))
                            .append("\n");
                }
            }
        }
        return storeBuilder.toString();
    }
}
