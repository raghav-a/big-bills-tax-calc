package com.bill.model;

import com.bill.model.testutils.ProductBuilder;
import org.junit.Test;

import java.io.StringWriter;

import static com.bill.model.testutils.ProductBuilder.Type.book;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ShoppingCartTest {

    @Test
    public void addItemsToShoppingCart() {
        new ShoppingCart().add(
            ProductBuilder.productOfType(book).name("book").price(15.95f).build(),
            2);
    }

    @Test
    public void checkoutShouldGenerateReceiptForItemsAddedToShoppingCart() {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(ProductBuilder.productOfType(book).name("book").price(15.95f).build(), 2);
        final Receipt receipt = shoppingCart.checkout();
        assertThat(receipt, notNullValue());
        StringWriter printer = new StringWriter();
        receipt.print(printer);
        assertThat(printer.toString(), is(
            "2 book: 31.90" + "\n" +
            "Sales Taxes : 0.00" + "\n" +
            "Total : 31.90"));

    }


}
