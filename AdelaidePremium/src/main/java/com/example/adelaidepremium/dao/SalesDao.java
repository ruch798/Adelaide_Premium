package com.example.adelaidepremium.dao;

import com.example.adelaidepremium.entity.Sales;
import javafx.collections.ObservableList;

public interface SalesDao {
        public ObservableList<Sales> getSales();
        public Sales getSalesId(int salesId);
        public ObservableList<Sales> getSalesByCustomerId(int customerId);
        public ObservableList<Sales> getSalesByOrderId(int orderId);
        public void insertSales(Sales sales);
}

