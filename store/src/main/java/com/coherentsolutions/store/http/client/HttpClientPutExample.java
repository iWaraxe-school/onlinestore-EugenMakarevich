package com.coherentsolutions.store.http.client;

import com.coherentsolutions.store.http.server.HttpServer;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HttpClientPutExample {
    public static void main(String[] args) throws Exception {
        new HttpServer().startServer();

        // Define the URL to the categories endpoint
        URL url = new URL("http://localhost:8000/categories/1"); // Assuming category ID is 1

        // Create an HttpURLConnection instance
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);

        // Set basic authentication header
        String auth = "admin" + ":" + "admin";
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + encodedAuth;
        connection.setRequestProperty("Authorization", authHeader);

        // Set the request body
        String requestBody = "50, SHOES";
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
            System.out.println("Category updated successfully");
        } else {
            System.out.println("Error: " + responseCode);
        }

        // Disconnect the connection
        connection.disconnect();
    }
}

