package com.coherentsolutions.store.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

public class HttpClientGetExample {
    public static void main(String[] args) throws IOException {
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
        connection.setRequestMethod("GET");

        // Send the request and receive the response
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response from the input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Process the response
            System.out.println("Response: " + response.toString());
        } else {
            // Handle the error response
            System.out.println("Error: " + responseCode);
        }

        // Disconnect the connection
        connection.disconnect();
    }
}

