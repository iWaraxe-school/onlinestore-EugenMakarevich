package com.coherentsolutions.store.print;

import com.coherentsolutions.store.db.DBConnection;

import java.sql.*;

import static com.coherentsolutions.store.db.DBConstants.SELECT_ALL_DATA_FROM_CATEGORIES;
import static com.coherentsolutions.store.db.DBConstants.SELECT_CATEGORY_PRODUCTS;

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

                try (PreparedStatement pstmt = conn.prepareStatement(SELECT_CATEGORY_PRODUCTS)) {
                    pstmt.setString(1, categoryId);
                    ResultSet rss = pstmt.executeQuery();

                    while (rss.next()) {
                        storeBuilder.append("- ")
                                .append(String.format("Name: %s, Rate: %.2f, Price: %.2f", rss.getString("name"), rss.getDouble("price"), rss.getDouble("rate")))
                                .append("\n");
                    }
                }
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return storeBuilder.toString();
    }
}
