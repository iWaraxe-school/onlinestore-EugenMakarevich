package com.coherentsolutions.store.httpserver;

import java.net.InetSocketAddress;

public class HttpServer {
    public static void main(String[] args) throws Exception {
        com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8000), 0);
        server.setExecutor(null); // Use the default executor

        // Define the server endpoints and their corresponding handlers
        server.createContext("/categories", new CategoriesHandler());
        server.createContext("/products", new ProductsHandler());

        // Start the server
        server.start();

        System.out.println("Server is running on port 8000");
    }
}

