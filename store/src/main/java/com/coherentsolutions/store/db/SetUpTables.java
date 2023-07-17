package com.coherentsolutions.store.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.coherentsolutions.store.db.DBConstants.*;
import static com.coherentsolutions.store.db.DbHandler.deleteTableData;

public class SetUpTables {
    Connection conn;
    Statement stmt;
    CheckTableExistance checkTable;

    public SetUpTables() throws SQLException {
        conn = DBConnection.getInstance().getConnection();
        stmt = conn.createStatement();
        checkTable = new CheckTableExistance();
    }

    public void createOrDeleteTable(String tableName, String createQuery) throws SQLException {
        if (!checkTable.isTableExist(tableName)) {
            stmt.executeUpdate(createQuery);
        } else {
            deleteTableData(tableName, conn);
        }
    }

    public void createCategoriesTable() throws SQLException {
        createOrDeleteTable("categories", CREATE_TABLE_CATEGORIES);
    }

    public void createProductsTable() throws SQLException {
        createOrDeleteTable("products", CREATE_TABLE_PRODUCTS);
    }

    public void createOrderTable() throws SQLException {
        createOrDeleteTable("orders", CREATE_TABLE_ORDERS);
    }
}
