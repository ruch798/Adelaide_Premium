package com.example.adelaidepremium.controller;

import com.example.adelaidepremium.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.Random;

public class SharedRandomCatalogueController {
    static long seed = new Random(121).nextLong();
    public static ObservableList<Product> PRODUCTLIST_DISCOUNTED;

    public static ObservableList<Product> generateDiscountedCatalogue(ObservableList<Product> PRODUCTLIST){
        PRODUCTLIST_DISCOUNTED = FXCollections.observableArrayList(PRODUCTLIST);
        Collections.shuffle(PRODUCTLIST_DISCOUNTED,new Random(seed));

        final int[] count = {0};

        PRODUCTLIST_DISCOUNTED.forEach(product -> {
            if(count[0] < 15){
                product.setPrice((int)(product.getPrice() * ((100- (new Random(seed).nextInt(5) * 10 + 10))/100.0)));
                count[0]++;
            }
        });
        return PRODUCTLIST_DISCOUNTED;
    }
}
