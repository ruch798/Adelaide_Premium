package com.example.adelaidepremium.dao;

import com.example.adelaidepremium.entity.Customer;
import javafx.collections.ObservableList;

/***
 * Customer DAO
 */
public interface CustomerDao {
  public ObservableList<Customer> getCustomers();

  public ObservableList<Customer> getDemographicTablesFromDatabase(int n);
}
