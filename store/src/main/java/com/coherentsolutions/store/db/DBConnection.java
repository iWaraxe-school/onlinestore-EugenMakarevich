package com.coherentsolutions.store.db;

import com.coherentsolutions.store.LoadProperties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;

    private DBConnection() throws SQLException {
        try {
            Properties properties = new LoadProperties().loadProperties();
            this.url = properties.getProperty("db.url");
            this.username = properties.getProperty("db.username");
            this.password = properties.getProperty("db.password");

            Class.forName(properties.getProperty("db.driver"));
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Database Connection Creation Failed", ex);
        } catch (IOException e) {
            throw new RuntimeException("Load Properties Operation Failed: " + e);
        }
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
