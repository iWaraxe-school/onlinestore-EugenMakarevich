package com.coherentsolutions.store.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ProductsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Implement the logic to handle products requests
        // Similar to the CategoriesHandler, handle different HTTP methods and perform corresponding actions
        // For example, handle GET requests to retrieve products, POST requests to add a new product, etc.
    }
}
