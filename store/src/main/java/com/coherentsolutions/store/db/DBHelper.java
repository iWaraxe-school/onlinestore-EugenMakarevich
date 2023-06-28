package com.coherentsolutions.store.db;

import java.sql.*;

import static com.coherentsolutions.store.db.DBConstants.*;

public class DBHelper {
    private Connection conn;
    private Statement stmt;

    public DBHelper() {
        try {
            conn = DBConnection.getInstance().getConnection();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public void createCategoriesTable() throws SQLException {
        if (!isTableExist("categories")) {
            stmt.executeUpdate(CREATE_TABLE_CATEGORIES);
        } else {
            stmt.executeUpdate(DELETE_ALL_DATA_FROM_CATEGORIES);
        }
    }

    public void createProductsTable() throws SQLException {
        if (!isTableExist("products")) {
            stmt.executeUpdate(CREATE_TABLE_PRODUCTS);
        } else {
            stmt.executeUpdate(DELETE_ALL_DATA_FROM_PRODUCTS);
        }
    }

    public boolean isTableExist(String tableName) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT count(*) "
                + "FROM information_schema.tables "
                + "WHERE table_name = ?"
                + "LIMIT 1;");
        preparedStatement.setString(1, tableName);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }

}
