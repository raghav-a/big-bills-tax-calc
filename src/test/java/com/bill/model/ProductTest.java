package com.bill.model;

import com.bill.model.testutils.ProductBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static com.bill.model.testutils.ProductBuilder.Type.*;
import static com.bill.model.testutils.ProductBuilder.productOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProductTest {

    @Test
    public void taxForBookIsZero() {
        final Product book = productOfType(ProductBuilder.Type.book).name("some").price(25.0f).build();
        assertThat(book.salesTax(), is(0.0f));
    }

    @Test
    public void taxForFoodItemIsZero() {
        final Product choclate = productOfType(food)
            .name("choclate")
            .price(25.0f)
            .build();
        assertThat(choclate.salesTax(), is(0.0f));
    }

    @Test
    public void taxForMedicalItemIsZero() {
        final Product medicine = productOfType(medical)
            .name("digene")
            .price(25.0f)
            .build();
        assertThat(medicine.salesTax(), is(0.0f));
    }


    @Test
    public void calculateTotalCost() {
        final Product shirt = productOfType(others).name("shirt").price(25.0f).build();
        assertThat(shirt.costPrice(), CoreMatchers.is(27.50f));
    }

    @Test
    public void roundOffSalesTax() {
        final Product shirt = productOfType(others).name("shirt").price(23.99f).build();
        assertThat(shirt.salesTax(), CoreMatchers.is(2.40f));

        final Product shirtTwo = productOfType(others).name("shirt").price(47.50f).isImported().build();
        assertThat(shirtTwo.salesTax(), CoreMatchers.is(7.15f));


        final Product perfume = productOfType(others).name("perfume").price(27.99f).isImported().build();
        assertThat(perfume.salesTax(), CoreMatchers.is(4.20f));
    }

}