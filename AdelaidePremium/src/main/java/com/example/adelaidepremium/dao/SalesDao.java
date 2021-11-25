package com.example.adelaidepremium.dao;

import com.example.adelaidepremium.entity.Sales;
import javafx.collections.ObservableList;

/***
 * Sales DAO
 */
public interface SalesDao {
  public ObservableList<Sales> getSales();

  public ObservableList<Sales> getSalesForQuarterFromDatabase(int n);

  public ObservableList<Sales> getTop10ProductsFromDatabase(String prodType);

  public void insertSales(Sales sales);
}
