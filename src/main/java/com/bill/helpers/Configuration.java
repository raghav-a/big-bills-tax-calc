package com.bill.helpers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static com.google.common.collect.Lists.newArrayList;

public class Configuration {

    public final List<String> taxFreeItems;
    public final Double salesTaxPercentage;
    public final Double additionalSalesTaxOnImportedItems;

    public Configuration(String configFile) {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream(configFile));
            taxFreeItems = ImmutableList.copyOf(newArrayList(properties.getProperty("tax-free-items").split(",")));
            salesTaxPercentage = Double.parseDouble(properties.getProperty("sales-tax-domestic"));
            additionalSalesTaxOnImportedItems = Double.parseDouble(properties.getProperty("additional-tax-on-imported-goods"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties", e);
        }
    }
}

