package com.example.bookunittesting.chapter02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class CustomerSolitaryTest {

    @Test
    void purchase_succeeds_when_enough_inventory() {
        final Store store = mock(Store.class);
        given(store.hasEnoughInventory(Product.Shampoo, 5))
                .willReturn(true);
        final Customer sut = new Customer();

        final boolean success = sut.purchase(store, Product.Shampoo, 5);

        assertThat(success).isTrue();
        verify(store, times(1))
                .removeInventory(Product.Shampoo, 5);
    }

    @Test
    void purchase_fails_when_enough_inventory() {
        final Store store = mock(Store.class);
        given(store.hasEnoughInventory(Product.Shampoo, 5))
                .willReturn(false);
        final Customer sut = new Customer();

        final boolean success = sut.purchase(store, Product.Shampoo, 5);

        assertThat(success).isFalse();
        verify(store, never())
                .removeInventory(Product.Shampoo, 5);
    }
}
