package com.bill.model;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartEntry> cartEntries = new ArrayList<>();

    public void add(Product product, Integer quantity) {
        cartEntries.add(CartEntry
            .cartEntry(product)
            .quantity(quantity)
            .build());
    }

    public Receipt checkout() {
        return new Receipt(ImmutableList.copyOf(cartEntries));
    }
}
