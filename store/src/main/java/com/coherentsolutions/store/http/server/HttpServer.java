package com.coherentsolutions.store.http.server;

import com.coherentsolutions.store.http.pages.CategoriesHandler;
import com.coherentsolutions.store.http.pages.ProductsHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServer {
    public void startServer() throws IOException {
        com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8000), 0);
        server.setExecutor(null); // Use the default executor

        // Separation of Concerns: Isolate the task of starting a server and defining endpoints
        // from making HTTP requests for readability and maintainability.

        // Define the server endpoints and their corresponding handlers
        server.createContext("/categories", new CategoriesHandler()).setAuthenticator(new Auth("MyRealm"));
        server.createContext("/products", new ProductsHandler()).setAuthenticator(new Auth("MyRealm"));

        // Start the server
        server.start();

        System.out.println("Server is running on port 8000");
    }
}

