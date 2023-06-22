package com.coherentsolutions.store.db;

import java.sql.*;

public class RegisterDriver {
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        //TODO Make it try-with-resources instead
        //Registering the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/onlinestore";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        System.out.println("Connection established: " + con);

        Statement stmt = con.createStatement();

        if (!tableExistsSQL(con, "categories")) {
            stmt.executeUpdate("CREATE TABLE categories (\n" +
                    "    id int,\n" +
                    "    category_name varchar(255)\n" +
                    ");");
        }

        if (!tableExistsSQL(con, "products")) {
            stmt.executeUpdate("CREATE TABLE products (\n" +
                    "    id int,\n" +
                    "    category_id int,\n" +
                    "    name varchar(255),\n" +
                    "    price decimal (10, 2),\n" +
                    "    rate decimal (5, 1)\n" +
                    ");");
        }
    }

    static boolean tableExistsSQL(Connection connection, String tableName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) "
                + "FROM information_schema.tables "
                + "WHERE table_name = ?"
                + "LIMIT 1;");
        preparedStatement.setString(1, tableName);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }
}
