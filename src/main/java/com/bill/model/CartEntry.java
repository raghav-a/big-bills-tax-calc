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
        return product.salesTax() * quantity();
    }


}
