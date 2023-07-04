package com.coherentsolutions.store.httpserver;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpClientPostExample {
    public static void main(String[] args) throws Exception {
        com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8000), 0);
        server.setExecutor(null); // Use the default executor

        // Define the server endpoints and their corresponding handlers
        server.createContext("/categories", new CategoriesHandler());
        server.createContext("/products", new ProductsHandler());

        // Start the server
        server.start();

        System.out.println("Server is running on port 8000");
        // Define the URL to the categories endpoint
        URL url = new URL("http://localhost:8000/categories");

        // Create an HttpURLConnection instance
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Set the request body
        String requestBody = "SHOES";
        byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);

        // Set the content type and content length headers
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
            System.out.println("Category added successfully");
        } else {
            System.out.println("Error: " + responseCode);
        }

        // Disconnect the connection
        connection.disconnect();
    }
}