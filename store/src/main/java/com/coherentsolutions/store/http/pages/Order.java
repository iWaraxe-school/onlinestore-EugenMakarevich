package com.coherentsolutions.store.http.pages;

import com.coherentsolutions.domain.products.Product;
import com.coherentsolutions.store.db.DbHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Order implements HttpHandler {
    private static final Logger logger = LogManager.getLogger(Order.class);
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Handling Http requests
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            post(exchange);
        }
    }

    private void post(HttpExchange exchange) throws IOException {
        // Read the request body
        BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));

        // The request body as a string
        String orderedProductInJson = reader.readLine();

        try {
            JsonObject json = (JsonObject) new JsonParser().parse(orderedProductInJson);
            String name = json.get("name").toString();
            double rate = json.get("rate").getAsDouble();
            double price = json.get("price").getAsDouble();
            Product orderedProduct = Product.newBuilder()
                    .setName(name)
                    .setPrice(price)
                    .setRate(rate)
                    .build();

            // Add order to the orders table
            DbHandler.addOrderToDb(orderedProduct);
        } catch (SQLException e) {
            logger.error("An SQL error occurs: ", e);
        }
    }
}
