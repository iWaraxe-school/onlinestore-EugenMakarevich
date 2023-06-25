package com.coherentsolutions.store.db;

import com.coherentsolutions.domain.categories.Categories;
import com.coherentsolutions.domain.products.Product;
import com.github.javafaker.Faker;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class RegisterDriver {

    static Faker faker = new Faker();

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        //TODO Make it try-with-resources instead
        //Registering the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/onlinestore";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        System.out.println("Connection established: " + con);

        Statement stmt = con.createStatement();

        if (!isTableExist(con, "categories")) {
            stmt.executeUpdate("CREATE TABLE categories (\n" +
                    "    id int,\n" +
                    "    category_name varchar(255)\n" +
                    ");");
        } else {
            stmt.executeUpdate("DELETE FROM categories");
        }

        if (!isTableExist(con, "products")) {
            stmt.executeUpdate("CREATE TABLE products (\n" +
                    "    id int,\n" +
                    "    category_id int,\n" +
                    "    name varchar(255),\n" +
                    "    price decimal (10, 2),\n" +
                    "    rate decimal (5, 1)\n" +
                    ");");
        } else {
            stmt.executeUpdate("DELETE FROM products");
        }

        fillStoreRandomly(con);
        createCategories(con);
    }

    static boolean isTableExist(Connection connection, String tableName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) "
                + "FROM information_schema.tables "
                + "WHERE table_name = ?"
                + "LIMIT 1;");
        preparedStatement.setString(1, tableName);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }

    public static void fillStoreRandomly(Connection con) throws SQLException {
        createCategories(con);
        int productNum = 10;

        Map<Integer, String> categories = getCategories(con);

        for (Map.Entry<Integer, String> entry : categories.entrySet()) {
            createProducts(entry.getKey(), entry.getValue(), productNum);
        }
    }

    public static void createCategories(Connection con) throws SQLException {
        int i = 0;
        for (Categories eCategory : Categories.values()) {
            i++;
            String query = "INSERT INTO categories (id, category_name) VALUES (" + i + ",'" + eCategory.toString() + "');";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        }
    }

    public static void createProducts(int category_id, String categoryName, int productNum) {
        for (int i = 0; i < productNum; i++) {
            Product product = generateProduct(i, category_id, categoryName);
            category.addProduct(product);
        }
    }

    public static Map<Integer, String> getCategories(Connection con) throws SQLException {
        String query = "select * from categories;";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);

        Map<Integer, String> categories = new HashMap<Integer, String>();
        if (rs.next()) {
            categories.put(rs.getInt("id"), rs.getString("category_name"));
        }
        return categories;
    }

    public static void generateProduct(int id, int category_id, String categoryName) {
        String query = "INSERT INTO products (id, category_id, name, price, rate) VALUES ("
                + id + ","
                + category_id + ",'"
                + generateName(categoryName) + "',"
                + generatePrice() + ","
                + generateRate() + ");";

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


}
