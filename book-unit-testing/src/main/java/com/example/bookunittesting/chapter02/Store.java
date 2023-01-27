package com.example.bookunittesting.chapter02;

public interface Store {

    boolean hasEnoughInventory(Product product, int quantity);
    void removeInventory(Product product, int quantity);
    void addInventory(Product product, int quantity);
    int getInventory(Product product);
}
