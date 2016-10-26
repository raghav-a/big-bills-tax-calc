package com.bill.helpers;

import com.bill.model.Product;

import java.util.List;

public class SalesTaxService {


    private final List<String> taxFreeItems;
    private final Double salesTaxPercentage;
    private final Double additionalSalesTaxOnImportedItems;


    public SalesTaxService(List<String> taxFreeItems, Double salesTaxPercentage, Double additionalSalesTaxOnImportedItems) {
        this.taxFreeItems = taxFreeItems;
        this.salesTaxPercentage = salesTaxPercentage;
        this.additionalSalesTaxOnImportedItems = additionalSalesTaxOnImportedItems;
    }

    public SalesTaxService(Configuration config) {
        this(config.taxFreeItems,config.salesTaxPercentage,config.additionalSalesTaxOnImportedItems);
    }


    public Float salesTaxFor(Product product) {
        float value = (salesTaxPercentage(product)* product.price()) / 100;
        return roundUp(value);

    }

    private Float salesTaxPercentage(Product product) {
        Float salesTax = taxFreeItems.contains(product.type()) ? 0 : salesTaxPercentage.floatValue();
        return salesTax + (product.isImported() ? additionalSalesTaxOnImportedItems.floatValue() : 0);
    }

    public static float roundUp(float x) {
        return (float) (Math.ceil(x * 20) / 20);
    }


}
