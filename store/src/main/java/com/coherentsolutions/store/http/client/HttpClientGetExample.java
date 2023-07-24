package com.coherentsolutions.store.http.client;

import com.coherentsolutions.store.http.server.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HttpClientGetExample {
    public static void main(String[] args) throws IOException {
        new HttpServer().startServer();

        // Define the URL to the categories endpoint
        URL url = new URL("http://localhost:8000/categories");

        // Create an HttpURLConnection instance
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set basic authentication header
        String auth = "admin" + ":" + "admin";
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + encodedAuth;

        // Set headers
        connection.setRequestProperty("Authorization", authHeader);
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

