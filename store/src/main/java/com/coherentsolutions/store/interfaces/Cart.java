package com.coherentsolutions.store.interfaces;

import com.coherentsolutions.domain.products.Product;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Cart {
    //Do I need to apply Singleton for cart???
    private static volatile Cart cart;

    private ConcurrentLinkedQueue<Product> cartItems = new ConcurrentLinkedQueue<>();

    private Cart() {
    }

    public static Cart getCart() {
        Cart result = cart;
        if (result != null) {
            return result;
        }
        synchronized (Cart.class) {
            if (cart == null) {
                cart = new Cart();
            }
            return cart;
        }
    }

    public void addCartItem(Product product) {
        cartItems.add(product);
    }

    public ConcurrentLinkedQueue<Product> getCartItems() {
        return cartItems;
    }
}
