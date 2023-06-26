package com.coherentsolutions.store.db;

import com.coherentsolutions.domain.categories.Categories;
import com.github.javafaker.Faker;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class RegisterDriver {
    static Connection conn;

    static Faker faker = new Faker();

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        conn = DBConnection.getInstance().getConnection();

        Statement stmt = conn.createStatement();

        if (!isTableExist("categories")) {
            stmt.executeUpdate("CREATE TABLE categories (\n" +
                    "    id int PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    category_name varchar(255)\n" +
                    ");");
        } else {
            stmt.executeUpdate("DELETE FROM categories");
        }

        if (!isTableExist("products")) {
            stmt.executeUpdate("CREATE TABLE products (\n" +
                    "    id int PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    category_id int,\n" +
                    "    name varchar(255),\n" +
                    "    price decimal (10, 2),\n" +
                    "    rate decimal (5, 1)\n" +
                    ");");
        } else {
            stmt.executeUpdate("DELETE FROM products");
        }

        fillStoreRandomly();
        System.out.println(printStore());
    }

    static boolean isTableExist(String tableName) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT count(*) "
                + "FROM information_schema.tables "
                + "WHERE table_name = ?"
                + "LIMIT 1;");
        preparedStatement.setString(1, tableName);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }

    public static void fillStoreRandomly() throws SQLException {
        createCategories();
        int productNum = 10;

        Map<Integer, String> categories = getCategories();

        for (Map.Entry<Integer, String> entry : categories.entrySet()) {
            createProducts(entry.getKey(), entry.getValue(), productNum);
        }
    }

    public static void createCategories() throws SQLException {
        for (Categories eCategory : Categories.values()) {
            String query = "INSERT INTO categories (category_name) VALUES (\"" + eCategory.toString() + "\");";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
        }
    }

    public static void createProducts(int category_id, String categoryName, int productNum) throws SQLException {
        for (int i = 1; i <= productNum; i++) {
            generateProduct(category_id, categoryName);
        }
    }

    public static Map<Integer, String> getCategories() throws SQLException {
        String query = "select * from categories;";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);

        Map<Integer, String> categories = new HashMap<Integer, String>();
        while (rs.next()) {
            categories.put(rs.getInt("id"), rs.getString("category_name"));
        }
        return categories;
    }

    public static void generateProduct(int category_id, String categoryName) throws SQLException {
        //Do I need to create separate Category, Product class for SQL representation?
        String query = "INSERT INTO products (category_id, name, price, rate) VALUES ("
                + category_id + ",\""
                + generateName(categoryName) + "\","
                + generatePrice() + ","
                + generateRate() + ");";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();
    }

    private static String generateName(String categoryName) {
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

    private static double generateRate() {
        return faker.number().randomDouble(1, 1, 10);
    }

    private static double generatePrice() {
        return faker.number().randomDouble(2, 3, 30);
    }

    public void isCategoryExist() {

    }

    public static String printStore() throws SQLException {
        StringBuilder storeBuilder = new StringBuilder();
        storeBuilder.append("Store:\n");

        String query = "SELECT * FROM categories";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            storeBuilder.append("Category: ").append(rs.getString("category_name")).append("\n");

            String categoryId = rs.getString("id");
            String q = "SELECT * FROM products where category_id = " + categoryId;

            Statement stm = conn.createStatement();
            ResultSet rss = stm.executeQuery(q);
            while (rss.next()) {
                storeBuilder.append("- ")
                        .append(String.format("Name: %s, Rate: %.2f, Price: %.2f", rss.getString("name"), rss.getDouble("price"), rss.getDouble("rate")))
                        .append("\n");
            }
        }
        return storeBuilder.toString();
    }
}
