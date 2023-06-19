package com.coherentsolutions.store.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RegisterDriver {
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        //Registering the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/world";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        System.out.println("Connection established: " + con);
    }
}
