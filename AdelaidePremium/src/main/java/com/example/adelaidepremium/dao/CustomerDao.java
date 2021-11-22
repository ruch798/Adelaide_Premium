package com.example.adelaidepremium.dao;

import com.example.adelaidepremium.entity.Customer;
import javafx.collections.ObservableList;

public interface CustomerDao {
    public ObservableList<Customer> getCustomers();
    public Customer getCustomerById(int customerId);
    public Customer getCustomerByName(String customerName);
}