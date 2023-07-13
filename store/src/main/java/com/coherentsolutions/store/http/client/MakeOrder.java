package com.coherentsolutions.store.http.client;

import com.coherentsolutions.domain.products.Product;
import com.coherentsolutions.store.db.DbHandler;
import com.coherentsolutions.store.http.server.HttpServer;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;

public class MakeOrder {

    public static void main(String[] args) throws Exception {
        clientMakesOrder();
    }

    public static void clientMakesOrder() throws SQLException, IOException {
        Product orderedProduct = new DbHandler().getRandomProduct();
        Gson g = new Gson();
        String productJson = g.toJson(orderedProduct);

        System.out.println(productJson);

        new HttpServer().startServer();

        // Define the URL to the categories endpoint
        URL url = new URL("http://localhost:8000/order");

        // Create an HttpURLConnection instance
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Set basic authentication header
        String auth = "admin" + ":" + "admin";
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + encodedAuth;
        connection.setRequestProperty("Authorization", authHeader);

        // Set the request body
        byte[] requestBodyBytes = productJson.getBytes(StandardCharsets.UTF_8);

        // Set headers
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Content-Length", String.valueOf(requestBodyBytes.length));

        // Send the request body
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.write(requestBodyBytes);
        outputStream.flush();
        outputStream.close();

        // Get the response code
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Product added successfully to a cart");
        } else {
            System.out.println("Error: " + responseCode);
        }

        // Disconnect the connection
        connection.disconnect();
    }
}
