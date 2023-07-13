package com.coherentsolutions.store.db;

public class DBConstants {
    public static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE categories (id int PRIMARY KEY AUTO_INCREMENT, category_name varchar(255));";
    public static final String DELETE_ALL_DATA_FROM_CATEGORIES = "DELETE FROM categories";
    public static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE products (id int PRIMARY KEY AUTO_INCREMENT, category_id int, name varchar(255), price decimal (10, 2), rate decimal (5, 1));";
    public static final String DELETE_ALL_DATA_FROM_PRODUCTS = "DELETE FROM products";
    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE orders (id int PRIMARY KEY AUTO_INCREMENT, name varchar(255), price decimal (10, 2), rate decimal (5, 1));";
    public static final String INSERT_INTO_ORDERS = "INSERT INTO orders (name, price, rate) VALUES (?, ?, ?);";
    public static final String DELETE_ALL_DATA_FROM_ORDERS = "DELETE FROM orders";
    public static final String SELECT_ALL_DATA_FROM_CATEGORIES = "SELECT * FROM categories";
    public static final String INSERT_INTO_PRODUCT = "INSERT INTO products (category_id, name, price, rate) VALUES (?, ?, ?, ?)";
    public static final String INSERT_INTO_CATEGORIES = "INSERT INTO categories (category_name) VALUES (?);";
    public static final String UPDATE_CATEGORY = "UPDATE categories SET category_name = ? WHERE id = ?;";
    public static final String DELETE_CATEGORY = "DELETE FROM categories WHERE id = ?";
    public static final String GET_RANDOM_PRODUCT = "SELECT * FROM products ORDER BY RAND() LIMIT 1;";
}
