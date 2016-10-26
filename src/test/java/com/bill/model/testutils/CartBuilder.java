package com.bill.model.testutils;

import com.bill.model.CartEntry;
import com.bill.model.Product;

public final class CartBuilder {
    private final Product product;
    private Integer quantity;

    private CartBuilder(Product product) {
        this.product = product;
    }

    public static CartBuilder cartEntry(ProductBuilder builder) {
        return new CartBuilder(builder.build());
    }

    public static CartBuilder cartEntry(Product product) {
        return new CartBuilder(product);
    }


    public CartBuilder quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public CartEntry build() {
        return new CartEntry(product, quantity);
    }
}
