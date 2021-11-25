package com.example.adelaidepremium.interfaces;

import com.example.adelaidepremium.entity.Sales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/***
 * Sales interface
 */
public interface SalesInterface {

    public ObservableList<Sales> SALESLIST = FXCollections.observableArrayList();
}
