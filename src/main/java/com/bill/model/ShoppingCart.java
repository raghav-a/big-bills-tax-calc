package com.bill.model;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartEntry> cartEntries = new ArrayList<>();

    public void add(Product product, Integer quantity) {
        cartEntries.add(new CartEntry(product,quantity));
    }

    public Receipt checkout() {
        return new Receipt(ImmutableList.copyOf(cartEntries));
    }
}
