package com.coherentsolutions.store.datageneration;

import com.coherentsolutions.domain.categories.Categories;
import com.coherentsolutions.store.db.DBConnection;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static com.coherentsolutions.store.db.DBConstants.INSERT_INTO_CATEGORIES;
import static com.coherentsolutions.store.db.DBConstants.SELECT_ALL_DATA_FROM_CATEGORIES;

public class RandomStorePopulatorDb {
    final int MAX_PRODUCTS_PER_CATEGORY = 10;
    RandomProductGeneratorDb dbProductGenerator = new RandomProductGeneratorDb();
    Connection conn;

    public RandomStorePopulatorDb() {
        conn = DBConnection.getInstance().getConnection();
    }

    public void fillStoreRandomly() throws SQLException {
        createCategories();

        Map<Integer, String> categories = getCategories();

        for (Map.Entry<Integer, String> entry : categories.entrySet()) {
            createProducts(entry.getKey(), entry.getValue(), MAX_PRODUCTS_PER_CATEGORY);
        }
    }

    public void createCategories() throws SQLException {
        for (Categories eCategory : Categories.values()) {
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_CATEGORIES);
            ps.setString(1, eCategory.toString());
            ps.executeUpdate();
        }
    }

    public void createProducts(int category_id, String categoryName, int productNum) throws SQLException {
        for (int i = 1; i <= productNum; i++) {
            dbProductGenerator.generateProduct(category_id, categoryName);
        }
    }

    public Map<Integer, String> getCategories() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SELECT_ALL_DATA_FROM_CATEGORIES);

        Map<Integer, String> categories = new HashMap<Integer, String>();
        while (rs.next()) {
            categories.put(rs.getInt("id"), rs.getString("category_name"));
        }
        return categories;
    }
}
