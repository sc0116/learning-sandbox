package com.example.bookunittesting.chapter03;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.bookunittesting.chapter02.Customer;
import com.example.bookunittesting.chapter02.OnlineStore;
import com.example.bookunittesting.chapter02.Product;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    void purchase_succeeds_when_enough_inventory() {
        final OnlineStore store = createStoreWithInventory(Product.Shampoo, 10);
        final Customer sut = createCustomer();

        final boolean success = sut.purchase(store, Product.Shampoo, 5);

        assertThat(success).isTrue();
        assertThat(store.getInventory(Product.Shampoo)).isEqualTo(5);
    }

    @Test
    void purchase_succeeds_when_not_enough_inventory() {
        final OnlineStore store = createStoreWithInventory(Product.Shampoo, 10);
        final Customer sut = createCustomer();

        final boolean success = sut.purchase(store, Product.Shampoo, 15);

        assertThat(success).isFalse();
        assertThat(store.getInventory(Product.Shampoo)).isEqualTo(10);
    }

    private OnlineStore createStoreWithInventory(final Product product, final int quantity) {
        final OnlineStore onlineStore = new OnlineStore();
        onlineStore.addInventory(product, quantity);

        return onlineStore;
    }

    private Customer createCustomer() {
        return new Customer();
    }
}
