package com.bill.model;

import com.bill.model.testutils.CartBuilder;
import com.bill.model.testutils.ProductBuilder;
import com.google.common.collect.ImmutableList;
import org.junit.After;
import org.junit.Test;

import java.io.StringWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bill.model.testutils.CartBuilder.cartEntry;
import static com.bill.model.testutils.ProductBuilder.Type.*;
import static com.bill.model.testutils.ProductBuilder.productOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReceiptTest {

    final StringWriter receiptPrinter = new StringWriter();

    @After
    public void tearDown(){
        receiptPrinter.flush();
    }


    @Test
    public void createReceiptWithCartEntries() {
        new Receipt(cartEntries(
            cartEntry(productOfType(book).name("bookName").price(12.45f))
                .quantity(3),
            cartEntry(productOfType(others).name("perfumeName").price(43.45f))
                .quantity(4))
        );
    }

    @Test
    public void printReceiptForOneBook_OneMusicCd_AndOneChoclateBar() {
        new Receipt(cartEntries(
            cartEntry(productOfType(book).name("book").price(12.49f))
                .quantity(1),
            cartEntry(productOfType(others).name("music cd").price(14.99f))
                .quantity(1),
            cartEntry(productOfType(food).name("chocolate bar").price(.85f))
                .quantity(1)
        )).print(receiptPrinter);

        assertThat(receiptPrinter.toString(),
            is("1 book: 12.49\n" +
                "1 music cd: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes : 1.50\n" +
                "Total : 29.83"));
    }


    @Test
    public void printReceiptForOneBoxImportedChocalte_AndOneImportedPerfume() {
        Receipt receipt = new Receipt(cartEntries(
            cartEntry(productOfType(food).name("imported box of chocolates").price(10.0f).isImported())
                .quantity(1),
            cartEntry(productOfType(others).name("imported bottle of perfume").price(47.5f).isImported())
                .quantity(1)));


        receipt.print(receiptPrinter);
        assertThat(receiptPrinter.toString(),
            is("1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes : 7.65\n" +
                "Total : 65.15")
        );
    }

    @Test
    public void printReceiptForOneImportedPerfume_OneDomesticPerfume_OnePacketOfPills_AndOneBoxImportedChocalte() {
        Receipt receipt = new Receipt(cartEntries(
            cartEntry(productOfType(others).name("imported bottle of perfume").price(27.99f).isImported())
                .quantity(1),
            cartEntry(productOfType(others).name("bottle of perfume").price(18.99f))
                .quantity(1),
            cartEntry(productOfType(medical).name("packet of headache pills").price(9.75f))
                .quantity(1),
            cartEntry(productOfType(food).name("box of imported chocolates").price(11.25f).isImported())
                .quantity(1))
        );

        receipt.print(receiptPrinter);
        assertThat(receiptPrinter.toString(),
            is("1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "1 box of imported chocolates: 11.85\n" +
                "Sales Taxes : 6.70\n" +
                "Total : 74.68"));
    }



    private static ImmutableList<CartEntry> cartEntries(CartBuilder... cartEntries) {
        return ImmutableList.copyOf(Stream.of(cartEntries).map(CartBuilder::build).collect(Collectors.toList()));
    }

}