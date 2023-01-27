package com.example.bookunittesting.chapter02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CustomerSociableTest {

    @Test
    void purchase_succeeds_when_enough_inventory() {
        final OnlineStore onlineStore = new OnlineStore();
        onlineStore.addInventory(Product.Shampoo, 10);
        final Customer sut = new Customer();

        final boolean success = sut.purchase(onlineStore, Product.Shampoo, 5);

        assertThat(success).isTrue();
        assertThat(onlineStore.getInventory(Product.Shampoo)).isEqualTo(5);
    }

    @Test
    void purchase_fails_when_enough_inventory() {
        final OnlineStore onlineStore = new OnlineStore();
        onlineStore.addInventory(Product.Shampoo, 10);
        final Customer sut = new Customer();

        final boolean success = sut.purchase(onlineStore, Product.Shampoo, 15);

        assertThat(success).isFalse();
        assertThat(onlineStore.getInventory(Product.Shampoo)).isEqualTo(10);
    }
}
