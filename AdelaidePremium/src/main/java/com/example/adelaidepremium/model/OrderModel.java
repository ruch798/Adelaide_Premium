package com.example.adelaidepremium.model;

import com.example.adelaidepremium.DatabaseConnection;
import com.example.adelaidepremium.dao.OrderDao;
import com.example.adelaidepremium.entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * Order Model
 */
public class OrderModel implements OrderDao {

  /***
   * get order object
   * @param rsSet
   * @return order object
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  private static Order getOrderObject(ResultSet rsSet) throws SQLException, ClassNotFoundException {

    try {
      Order order = new Order();

      while (rsSet.next()) {
        order.setId(rsSet.getInt("order_id"));
        order.setCustomerId(rsSet.getInt("customer_id"));
        order.setPayment(rsSet.getInt("payment"));
        order.setDate(rsSet.getString("order_date"));
        order.setDeliveryDate(rsSet.getString("delivery_date"));
      }
      return order;
    } catch (SQLException e) {
      System.out.println("Error occurred while fetching data!");
      e.printStackTrace();
      throw e;
    }
  }

  /***
   * get order objects
   * @param rsSet
   * @return list of orders
   */
  private static ObservableList<Order> getOrderObjects(ResultSet rsSet) {

    try {
      ObservableList<Order> orderlist = FXCollections.observableArrayList();

      while (rsSet.next()) {
        Order order = new Order();
        order.setId(rsSet.getInt("order_id"));
        order.setCustomerId(rsSet.getInt("customer_id"));
        order.setPayment(rsSet.getInt("payment"));
        order.setDate(rsSet.getString("order_date"));
        order.setDeliveryDate(rsSet.getString("delivery_date"));
        orderlist.add(order);
      }
      return orderlist;
    } catch (SQLException e) {
      System.out.println("Error occurred while fetching data!");
      e.printStackTrace();
      return null;
    }
  }

  /***
   * get order data from database
   * @return list of orders
   */
  public ObservableList<Order> getOrders() {
    String sql = "select * from orders";
    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      ObservableList<Order> orderlist = getOrderObjects(rs);
      return orderlist;
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /***
   * get order data from database using order id
   * @param orderId
   * @return order object
   */
  public Order getOrder(int orderId) {
    String sql = "select * from orders where order_id = " + orderId + "";
    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      Order order = getOrderObject(rs);
      return order;
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("Record not found");
      return null;
    }
  }

  /***
   * get order data from database using customer id
   * @param customerId
   * @return list of orders
   */
  public ObservableList<Order> getOrderByCustomerId(int customerId) {
    String sql = "select * from orders where customer_id = " + customerId + "";
    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      ObservableList<Order> orderlist = getOrderObjects(rs);
      return orderlist;
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("Record not found");
      return null;
    }
  }

  /***
   * insert order into database
   * @param order
   */
  public void insertOrder(Order order) {

    String sql =
        "insert into orders values("
            + order.getId()
            + ", "
            + order.getCustomerId()
            + ", "
            + order.getPayment()
            + ",\""
            + order.getDate()
            + "\", \""
            + order.getDeliveryDate()
            + "\")";
    System.out.println(sql);
    try {
      DatabaseConnection.database_executeQuery(sql);
    } catch (SQLException | ClassNotFoundException e) {
      System.out.println("Exception occurred while inserting data");
    }
  }
}
