package com.coherentsolutions.store.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckTableExistance {
    private Connection conn;

    public CheckTableExistance() throws SQLException {
        conn = DBConnection.getInstance().getConnection();
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
