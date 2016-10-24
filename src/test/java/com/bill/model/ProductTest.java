package com.bill.model;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theory;

import static com.bill.model.Product.Type.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProductTest {

    @Test
    public void taxForBookIsZero() {
        final Product book = Product.ofType(Product.Type.book).name("some").price(25.0f).build();
        assertThat(book.salesTaxPerItem(), is(0.0f));
    }

    @Test
    public void taxForFoodItemIsZero() {
        final Product choclate = Product.ofType(food)
            .name("choclate")
            .price(25.0f)
            .build();
        assertThat(choclate.salesTaxPerItem(), is(0.0f));
    }

    @Test
    public void taxForMedicalItemIsZero() {
        final Product medicine = Product.ofType(medical)
            .name("digene")
            .price(25.0f)
            .build();
        assertThat(medicine.salesTaxPerItem(), is(0.0f));
    }

    @Test
    public void calculateTaxForOtherItems() {
        final Product shirt = Product.ofType(others)
            .name("shirt")
            .price(25.0f)
            .build();
        assertThat(shirt.salesTaxPerItem(), is(2.5f));
    }


    @DataPoint
    Product[] domesticProducts = {
        Product.ofType(book).name("some").price(23.0f).build(),
        Product.ofType(food).name("some").price(23.0f).build(),
        Product.ofType(medical).name("some").price(23.0f).build(),
        Product.ofType(others).name("some").price(23.0f).build()
    };

    @DataPoint
    Product[] importedProducts = {
        Product.ofType(book).name("some").price(23.0f).isImported().build(),
        Product.ofType(food).name("some").price(23.0f).isImported().build(),
        Product.ofType(medical).name("some").price(23.0f).isImported().build(),
        Product.ofType(others).name("some").price(23.0f).isImported().build()
    };

    @Theory
    public void taxForImportedItemIsFivePercentHigher(Product[] domesticProducts, Product[] importedProducts) {
        for (int i = 0; i < importedProducts.length; i++)
            assertThat(importedProducts[i].salesTaxPercentage() - domesticProducts[i].salesTaxPercentage(), is(5.0f));
    }

    @Test
    public void calculateTotalCost() {
        final Product shirt = Product.ofType(others).name("shirt").price(25.0f).build();
        assertThat(shirt.costPrice(), CoreMatchers.is(27.50f));
    }

    @Test
    public void roundOffSalesTax() {
        final Product shirt = Product.ofType(others).name("shirt").price(23.99f).build();
        assertThat(shirt.salesTaxPerItem(), CoreMatchers.is(2.40f));

        final Product shirtTwo = Product.ofType(others).name("shirt").price(47.50f).isImported().build();
        assertThat(shirtTwo.salesTaxPerItem(), CoreMatchers.is(7.15f));


        final Product perfume = Product.ofType(others).name("perfume").price(27.99f).isImported().build();
        assertThat(perfume.salesTaxPerItem(), CoreMatchers.is(4.20f));
    }

}