package com.example.adelaidepremium.interfaces;

import com.example.adelaidepremium.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/***
 * Product interface
 */
public interface ProductInterface {

    public ObservableList<Product> PRODUCTLIST = FXCollections.observableArrayList();
}
