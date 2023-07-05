package com.coherentsolutions.store.http.pages;

import com.coherentsolutions.store.db.DBConnection;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.coherentsolutions.store.db.DBConstants.*;

public class CategoriesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Handling Http method requests
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            get(exchange);
        } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            post(exchange);
        } else if (exchange.getRequestMethod().equalsIgnoreCase("PUT")) {
            put(exchange);
        } else if (exchange.getRequestMethod().equalsIgnoreCase("DELETE")) {
            delete(exchange);
        }
    }

    public void get(HttpExchange exchange) throws IOException {
        List<String> categories = new ArrayList<>();

        // Retrieve categories from the database;
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_DATA_FROM_CATEGORIES);

            while (rs.next()) {
                categories.add(rs.getString("category_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Make response body
        String response = "";

        for (String category : categories) {
            response += category + " ";
        }
        // Set the response headers
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.getBytes().length);

        // Send the response body
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private void post(HttpExchange exchange) throws IOException {
        // Read the request body
        BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();

        // The request body as a string
        String category = requestBody.toString();

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_DATA_FROM_CATEGORIES);
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_CATEGORIES);
            ps.setString(1, category);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void put(HttpExchange exchange) throws IOException {
        // Read the request body
        BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();

        // The request body as a string
        String requestBodyString = requestBody.toString();
        String[] splitValues = requestBodyString.split(",");

        // Accessing the individual values
        int categoryId = Integer.parseInt(splitValues[0].trim());
        String newCategoryName = splitValues[1].trim();

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_CATEGORY);
            ps.setInt(2, categoryId);
            ps.setString(1, newCategoryName);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(HttpExchange exchange) throws IOException {
        // Read the request body
        BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();

        // The request body as a string
        int categoryId = Integer.parseInt(requestBody.toString());

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_CATEGORY);
            ps.setInt(1, categoryId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

