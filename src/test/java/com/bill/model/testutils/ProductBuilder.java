package com.bill.model.testutils;

import com.bill.helpers.Configuration;
import com.bill.model.Product;
import com.bill.helpers.SalesTaxService;

public final class ProductBuilder {

    private static SalesTaxService salesTaxService = new SalesTaxService(new Configuration("settings.properties"));


    public static final class  Type {
        public static String book="book";
        public static String medical = "medical";
        public static String food = "food";
        public static String others = "others";
    }

    private String name;
    private Float price;
    private String type;

    private boolean isImported = false;

    ProductBuilder(String type) {
        this.type = type;
    }


    public static ProductBuilder productOfType(String type) {
        return new ProductBuilder(type);
    }

    public ProductBuilder isImported() {
        this.isImported = true;
        return this;
    }

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }


    public ProductBuilder price(Float price) {
        this.price = price;
        return this;
    }
    public Product build() {
        return new Product(name, type,  isImported, price, salesTaxService);
    }


}
