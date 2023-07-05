package com.coherentsolutions.store.http.client;

import com.coherentsolutions.store.http.server.HttpServer;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpClientPostExample {
    public static void main(String[] args) throws Exception {
        new HttpServer().startServer();

        System.out.println("Server is running on port 8000");
        // Define the URL to the categories endpoint
        URL url = new URL("http://localhost:8000/categories");

        // Create an HttpURLConnection instance
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Set the request body
        String requestBody = "NEW CATEGORY";
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
