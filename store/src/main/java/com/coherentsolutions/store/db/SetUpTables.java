package com.coherentsolutions.store.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.coherentsolutions.store.db.DBConstants.*;

public class SetUpTables {
    Connection conn;
    Statement stmt;
    CheckTableExistance checkTable;

    public SetUpTables() throws SQLException {
        conn = DBConnection.getInstance().getConnection();
        stmt = conn.createStatement();
        checkTable = new CheckTableExistance();
    }

    public void createCategoriesTable() throws SQLException {
        if (!checkTable.isTableExist("categories")) {
            stmt.executeUpdate(CREATE_TABLE_CATEGORIES);
        } else {
            stmt.executeUpdate(DELETE_ALL_DATA_FROM_CATEGORIES);
        }
    }

    public void createProductsTable() throws SQLException {
        if (!checkTable.isTableExist("products")) {
            stmt.executeUpdate(CREATE_TABLE_PRODUCTS);
        } else {
            stmt.executeUpdate(DELETE_ALL_DATA_FROM_PRODUCTS);
        }
    }
}
