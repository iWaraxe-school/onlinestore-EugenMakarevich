package com.coherentsolutions.store;

import com.coherentsolutions.store.interfaces.Cart;

public class ClearPurchasedGoodsTask implements Runnable {
    Store store;
    Cart cart;

    public ClearPurchasedGoodsTask(Store store) {
        this.store = store;
        cart = Cart.getCart();
    }


    @Override
    public void run() {
        if (!cart.getCartItems().isEmpty()) {
            System.out.println("Cart is going to be cleared: " + cart.getCartItems());
            cart.getCartItems().clear();
            System.out.println("Cart is cleared: " + cart.getCartItems());
        }
    }
}
