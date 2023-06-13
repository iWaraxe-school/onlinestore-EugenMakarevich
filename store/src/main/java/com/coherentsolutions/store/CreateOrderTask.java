package com.coherentsolutions.store;

import com.coherentsolutions.domain.products.Product;
import com.coherentsolutions.store.interfaces.Cart;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateOrderTask implements Runnable {
    Store store;
    Cart cart;

    public CreateOrderTask(Store store) {
        this.store = store;
        cart = Cart.getCart();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Command = order");

        // 1. Get list of all products
        List<Product> products = store.getAllProducts();
        // 2. Choose random product
        Random randomizer = new Random();
        Product orderedProduct = products.get(randomizer.nextInt(products.size()));
        System.out.println("Ordered product:" + orderedProduct);
        // 3. Add the product to the cart
        cart.addCartItem(orderedProduct);
        System.out.println("Cart:" + cart.getCartItems());
        // 4. Process the order for the specified time
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " End.");
    }
}
