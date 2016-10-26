package com.bill.model;

import com.bill.helpers.SalesTaxService;

public class Product {

    private final Boolean isImported;
    private final Float price;
    private final String type;
    private final String name;
    private final Float salesTax;
    private final Float costPrice;


    public Product(String name, String type, Boolean isImported, Float price, SalesTaxService salesTaxService) {
        this.isImported = isImported;
        this.price = price;
        this.type = type;
        this.name = name;
        this.salesTax = salesTaxService.salesTaxFor(this);
        this.costPrice = price() + salesTax();
    }

    public String name() {
        return name;
    }

    public Float price() {
        return price;
    }

    public float costPrice() {
            return costPrice;
    }

    public Float salesTax() {
       return salesTax;
    }

    public String type() {
        return type;
    }

    public Boolean isImported() {
        return isImported;
    }

}
