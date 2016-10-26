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

}