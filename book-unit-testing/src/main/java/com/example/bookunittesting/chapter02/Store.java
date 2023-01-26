package com.example.bookunittesting.chapter02;

import java.util.HashMap;
import java.util.Map;

public class Store {

    private final Map<Product, Integer> inventory = new HashMap<>();

    public boolean hasEnoughInventory(final Product product, final int quantity) {
        return getInventory(product) >= quantity;
    }

    public void removeInventory(final Product product, final int quantity) {
        if (!hasEnoughInventory(product, quantity)) {
            throw new IllegalStateException("Not enough inventory");
        }
        inventory.computeIfPresent(product, (key, value) -> value - quantity);
    }

    public void addInventory(final Product product, final int quantity) {
        if (inventory.containsKey(product)) {
            inventory.computeIfPresent(product, (key, value) -> value + quantity);
        }
        inventory.put(product, quantity);
    }

    public int getInventory(final Product product) {
        return inventory.getOrDefault(product, 0);
    }
}
