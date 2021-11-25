package com.example.adelaidepremium.interfaces;

import com.example.adelaidepremium.entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/***
 * Customer interface
 */
public interface CustomerInterface {

    public ObservableList<Customer> CUSTOMERSLIST = FXCollections.observableArrayList();
}