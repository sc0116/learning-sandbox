package com.example.bookunittesting.chapter02;

public class Customer {

    public boolean purchase(final Store store, final Product product, final int quantity) {
        if (!store.hasEnoughInventory(product, quantity)) {
            return false;
        }

        store.removeInventory(product, quantity);
        return true;
    }
}
