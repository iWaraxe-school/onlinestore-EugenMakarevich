package com.coherentsolutions.store.interfaces;

import com.coherentsolutions.domain.products.Product;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Cart {
    //Do I need to apply Singleton for cart??? - Not really

    private ConcurrentLinkedQueue<Product> cartItems = new ConcurrentLinkedQueue<>();

    private Cart() {
    }

    public static Cart getCart() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final Cart INSTANCE = new Cart();
    }

    public void addCartItem(Product product) {
        cartItems.add(product);
    }

    public ConcurrentLinkedQueue<Product> getCartItems() {
        return cartItems;
    }
}
