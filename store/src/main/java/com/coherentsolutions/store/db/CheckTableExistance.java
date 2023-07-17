package com.coherentsolutions.store.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.coherentsolutions.store.db.DBConstants.CHECK_IF_TABLE_EXISTS;

public class CheckTableExistance {
    private Connection conn;

    public CheckTableExistance() {
        conn = DBConnection.getInstance().getConnection();
    }

    public boolean isTableExist(String tableName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(CHECK_IF_TABLE_EXISTS);
        ps.setString(1, tableName);

        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }
}
