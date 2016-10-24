package com.bill.model;

public class CartEntry {

    private final static String billLineTemplate = "%s %s: %.2f";

    private final Product product;
    private final Integer quantity;

    public CartEntry(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    public String receiptLine() {
        return String.format(billLineTemplate, quantity(), product.name(), costForAllItems());
    }

    public Float costForAllItems() {
        return product.costPrice() * quantity();
    }


    public Integer quantity() {
        return quantity;
    }


    public Float totalSalesTax() {
        return product.salesTaxPerItem() * quantity();
    }


    public static Builder cartEntry(Product product) {
        return new Builder(product);
    }

    public static Builder cartEntry(Product.Builder builder) {
        return new Builder(builder.build());
    }


    public static final class Builder {
        private final Product product;
        private Integer quantity;

        private Builder(Product product) {
            this.product = product;
        }


        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public CartEntry build() {
            return new CartEntry(product, quantity);
        }
    }

}
