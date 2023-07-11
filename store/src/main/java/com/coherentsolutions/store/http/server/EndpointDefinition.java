package com.coherentsolutions.store.http.server;

import com.coherentsolutions.store.http.pages.CategoriesHandler;
import com.coherentsolutions.store.http.pages.ProductsHandler;

public class EndpointDefinition {
    public void defineEndpoints(com.sun.net.httpserver.HttpServer server) {
        // Define the server endpoints and their corresponding handlers
        server.createContext("/categories", new CategoriesHandler()).setAuthenticator(new Auth("MyRealm"));
        server.createContext("/products", new ProductsHandler()).setAuthenticator(new Auth("MyRealm"));
    }
}