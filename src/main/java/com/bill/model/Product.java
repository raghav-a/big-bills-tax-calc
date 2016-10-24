package com.bill.model;

public class Product {
    enum Type {
        book(0.00f), medical(0.00f), food(0.00f), others(10.00f);

        private Float salesTax;

        Type(Float salesTax) {
            this.salesTax = salesTax;
        }

    }

    private final Float price;
    private final Type type;
    private final String name;

    private final Float salesTaxPercentage;

    public Product(String name, Type type, Boolean isImported, Float price) {
        this.price = price;
        this.type = type;
        this.name = name;
        salesTaxPercentage = type.salesTax + (isImported ? 5.00f : 0.00f);
    }

    public static Builder ofType(Type type) {
        return new Builder(type);
    }

    public String name() {
        return name;
    }

    public Float price() {
        return price;
    }

    public float costPrice() {
            return price() + salesTaxPerItem();
    }

    public Float salesTaxPerItem() {
        final float value = (salesTaxPercentage * price()) / 100;
        return roundOff(value);
    }

    public Float salesTaxPercentage() {
        return salesTaxPercentage;
    }

    private Float roundOff(double num) {
        return Math.round(num * (20)) / 20.00f;
    }

    public static final class Builder {
        private String name;
        private Float price;
        private Type type;
        private boolean isImported = false;

        private Builder(Type type) {
            this.type = type;
        }

        public Builder isImported() {
            this.isImported = true;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Float price) {
            this.price = price;
            return this;
        }


        public Product build() {
            return new Product(name, type,  isImported, price);
        }
    }
}
