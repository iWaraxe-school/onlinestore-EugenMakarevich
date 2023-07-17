package com.coherentsolutions.store.db;

import com.coherentsolutions.store.LoadProperties;
import com.coherentsolutions.store.http.pages.CategoriesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final Logger logger = LogManager.getLogger(CategoriesHandler.class);
    private static DBConnection instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;

    private DBConnection() {
        try {
            Properties properties = new LoadProperties().loadProperties();
            this.url = properties.getProperty("db.url");
            this.username = properties.getProperty("db.username");
            this.password = properties.getProperty("db.password");

            Class.forName(properties.getProperty("db.driver"));
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            logger.error("Database Connection Creation Failed", e);
        } catch (IOException e) {
            logger.error("Load Properties Operation Failed: " + e);
        } catch (SQLException e) {
            logger.error("SQL exception occurred: " + e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DBConnection();
                }
            } catch (SQLException e) {
                logger.error("SQL exception occurred: " + e);
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
