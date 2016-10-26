package com.bill.model;

import com.google.common.collect.ImmutableList;

import java.io.*;

public class Receipt {
    private final Double totalSalesTax;
    private final Double totalAmount;
    private ImmutableList<CartEntry> cartEntries;
    private String salesTaxtemplate = "Sales Taxes : %.2f";
    private String totaPriceTemplate = "Total : %.2f";

    public Receipt(ImmutableList<CartEntry> cartEntries) {
        this.cartEntries = cartEntries;
        this.totalSalesTax = totalSalesTax();
        this.totalAmount = totalAmount();
    }

    public void print(Writer writer) {
        cartEntries.forEach(entry -> write(writer, entry.receiptLine() + "\n"));
        write(writer, String.format(salesTaxtemplate, totalSalesTax) + "\n");
        write(writer, String.format(totaPriceTemplate, totalAmount));


    }

    private void write(Writer writer, String str){
        try {
            writer.write(str);
        } catch (IOException e) {
            throw new RuntimeException("Could not write to writer.",e);
        }
    }

    private Double totalAmount() {
        return cartEntries
            .stream()
            .mapToDouble(CartEntry::costForAllItems)
            .sum();
    }

    private Double totalSalesTax() {
        return cartEntries
            .stream()
            .mapToDouble(CartEntry::totalSalesTax)
            .sum();

    }
}
