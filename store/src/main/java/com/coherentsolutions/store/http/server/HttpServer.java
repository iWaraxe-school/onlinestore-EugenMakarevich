package com.coherentsolutions.store.http.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

public class HttpServer {
    public void startServer() throws IOException {
        com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8000), 0);

        // Create and configure a ThreadPoolExecutor
        Executor executor = new ThreadPoolExecutorSetUp().createThreadPoolExecutor();

        // Set the ThreadPoolExecutor as the executor
        server.setExecutor(executor);

        // Define the server endpoints and their corresponding handlers
        new EndpointDefinition().defineEndpoints(server);

        // Start the server
        server.start();

        System.out.println("Server is running on port 8000");
    }
}

