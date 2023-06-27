package com.coherentsolutions.store.datageneration;

import com.coherentsolutions.domain.categories.Categories;
import com.coherentsolutions.store.db.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static com.coherentsolutions.store.db.DBConstants.SELECT_ALL_DATA_FROM_CATEGORIES;

public class RandomStorePopulatorDb extends DBHelper {
    RandomProductGeneratorDb dbProductGenerator = new RandomProductGeneratorDb();

    public void fillStoreRandomly() throws SQLException {
        createCategories();
        int productNum = 10;

        Map<Integer, String> categories = getCategories();

        for (Map.Entry<Integer, String> entry : categories.entrySet()) {
            createProducts(entry.getKey(), entry.getValue(), productNum);
        }
    }

    public void createCategories() throws SQLException {
        for (Categories eCategory : Categories.values()) {
            PreparedStatement ps = getConn().prepareStatement("INSERT INTO categories (category_name) VALUES (\"" + eCategory.toString() + "\");");
            ps.executeUpdate();
        }
    }

    public void createProducts(int category_id, String categoryName, int productNum) throws SQLException {
        for (int i = 1; i <= productNum; i++) {
            dbProductGenerator.generateProduct(category_id, categoryName);
        }
    }

    public Map<Integer, String> getCategories() throws SQLException {
        Statement stmt = getConn().createStatement();
        ResultSet rs = stmt.executeQuery(SELECT_ALL_DATA_FROM_CATEGORIES);

        Map<Integer, String> categories = new HashMap<Integer, String>();
        while (rs.next()) {
            categories.put(rs.getInt("id"), rs.getString("category_name"));
        }
        return categories;
    }
}
