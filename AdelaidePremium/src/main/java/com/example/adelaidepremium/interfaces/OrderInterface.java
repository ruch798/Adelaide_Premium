package com.example.adelaidepremium.interfaces;

import com.example.adelaidepremium.entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface OrderInterface {

    public ObservableList<Order> ORDERSLIST = FXCollections.observableArrayList();
}
