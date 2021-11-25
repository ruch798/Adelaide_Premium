package com.example.adelaidepremium.dao;

import com.example.adelaidepremium.entity.Order;
import javafx.collections.ObservableList;

/***
 * Order DAO
 */
public interface OrderDao {
  public ObservableList<Order> getOrders();

  public Order getOrder(int orderId);

  public ObservableList<Order> getOrderByCustomerId(int customerId);

  public void insertOrder(Order order);
}
