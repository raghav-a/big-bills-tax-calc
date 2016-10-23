package com.bill;

import com.bill.model.Item;
import com.bill.model.ShoppingCart;
import org.junit.Test;

public class ShoppingCartTest {

    @Test
    public void addItemsToShoppingCart(){
        new ShoppingCart().add(new Item(){

        });
    }
}
