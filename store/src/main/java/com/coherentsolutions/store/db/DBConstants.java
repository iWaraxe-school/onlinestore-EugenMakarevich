package com.coherentsolutions.store.db;

public class DBConstants {
    public static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE categories (id int PRIMARY KEY AUTO_INCREMENT, category_name varchar(255));";
    public static final String DELETE_ALL_DATA_FROM_CATEGORIES = "DELETE FROM categories";
    public static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE products (id int PRIMARY KEY AUTO_INCREMENT, category_id int, name varchar(255), price decimal (10, 2), rate decimal (5, 1));";
    public static final String DELETE_ALL_DATA_FROM_PRODUCTS = "DELETE FROM products";
    public static final String SELECT_ALL_DATA_FROM_CATEGORIES = "SELECT * FROM categories";
}
