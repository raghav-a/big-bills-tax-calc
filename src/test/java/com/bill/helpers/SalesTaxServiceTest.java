package com.bill.helpers;

import com.bill.model.Product;
import com.bill.model.testutils.ProductBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SalesTaxServiceTest {

    private Configuration configuration = new Configuration("application.properties");
    private SalesTaxService salesTaxService = new SalesTaxService(configuration);



    @Test
    public void calculateSalesTaxForExemptedDomesticItem(){
        final String type = configuration.taxFreeItems.stream().findAny().get();
        final Product book = ProductBuilder.productOfType(type).name("some item").price(20.0f).build();
        assertThat(salesTaxService.salesTaxFor(book), is(0.0f));
    }

    @Test
    public void calculateSalesTaxForNonExemptedDomesticItems(){
        final Product perfume = ProductBuilder.productOfType("perfume").name("perfume").price(20.0f).build();
        assertThat(salesTaxService.salesTaxFor(perfume), is(2.0f));
    }

    @Test
    public void calculateSalesTaxForExemptedButImportedItem(){
        final String type = configuration.taxFreeItems.stream().findAny().get();
        final Product book = ProductBuilder.productOfType(type).name("some item").price(20.0f).isImported().build();
        assertThat(salesTaxService.salesTaxFor(book), is(1.0f));
    }

    @Test
    public void calculateSalesTaxForTaxableImportedItem(){
        final Product book = ProductBuilder.productOfType("perfume").name("perfume").price(20.0f).isImported().build();
        assertThat(salesTaxService.salesTaxFor(book), is(3.0f));
    }

    @Test
    public void validateRoundUp(){
        assertThat(SalesTaxService.roundUp(.56f), is(.60f));
        assertThat(SalesTaxService.roundUp(7.625f), is(7.65f));
    }

}