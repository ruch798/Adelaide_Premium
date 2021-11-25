package com.example.adelaidepremium.model;

import com.example.adelaidepremium.DatabaseConnection;
import com.example.adelaidepremium.entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * Customer Model
 */
public class CustomerModel {

  /***
   * get Customer Objects
   * @param rsSet
   * @return list of customers
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  private static ObservableList<Customer> getCustomerObjects(ResultSet rsSet)
      throws SQLException, ClassNotFoundException {
    try {
      ObservableList<Customer> customerlist = FXCollections.observableArrayList();

      while (rsSet.next()) {
        Customer customer = new Customer();
        customer.setId(rsSet.getInt("customer_id"));
        customer.setName(rsSet.getString("customer_name"));
        customer.setGender(rsSet.getString("gender"));
        customer.setAge(rsSet.getInt("age"));
        customer.setHomeAddress(rsSet.getString("home_address"));
        customer.setZipCode(rsSet.getInt("zip_code"));
        customer.setCity(rsSet.getString("city"));
        customer.setState(rsSet.getString("state"));
        customer.setCountry(rsSet.getString("country"));
        customerlist.add(customer);
      }
      return customerlist;
    } catch (SQLException e) {
      System.out.println("Error occurred while fetching data!");
      e.printStackTrace();
      return null;
    }
  }

  /***
   * get Demographic Tables
   * @param rsSet
   * @param n
   * @return list of customers and their purchase data
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  private static ObservableList<Customer> getDemographicTables(ResultSet rsSet, int n)
      throws SQLException, ClassNotFoundException {
    try {
      ObservableList<Customer> customerList = FXCollections.observableArrayList();

      while (rsSet.next()) {
        if (n == 1) {
          Customer customer = new Customer(null, null, null, 0);
          customer.setGender(rsSet.getString("gender"));
          customer.setPName(rsSet.getString("product_name"));
          customer.setPType(rsSet.getString("product_type"));
          customer.setPQuantity(rsSet.getInt("product_quantity"));
          customerList.add(customer);
        } else if (n == 2) {
          Customer customer = new Customer(0, null, null, 0);
          customer.setAge(rsSet.getInt("age"));
          customer.setPName(rsSet.getString("product_name"));
          customer.setPType(rsSet.getString("product_type"));
          customer.setPQuantity(rsSet.getInt("product_quantity"));
          customerList.add(customer);
        } else if (n == 3) {
          Customer customer = new Customer(null, 0);
          customer.setState(rsSet.getString("state"));
          customer.setPQuantity(rsSet.getInt("product_quantity"));
          customerList.add(customer);
        }
      }
      return customerList;
    } catch (SQLException e) {
      System.out.println("Error occurred while fetching data!");
      e.printStackTrace();
      return null;
    }
  }

  /***
   * fetch customers data from database
   * @return list of customers
   */
  public ObservableList<Customer> getCustomers() {
    String sql = "select * from customers";
    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      ObservableList<Customer> customerlist = getCustomerObjects(rs);
      return customerlist;
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /***
   * fetch customers demographic data from database
   * @param n
   * @return list of customers and their purchase data
   */
  public ObservableList<Customer> getDemographicTablesFromDatabase(int n) {
    String sql = null;
    //    gender
    if (n == 1) {
      sql =
          "select c.gender, p.product_name, p.product_type, max(s.quantity) as product_quantity from customers as c  inner join orders as o on c.customer_id = o.customer_id inner join sales as s on s.order_id = o.order_id inner join products as p on s.product_id = p.product_id group by c.gender, s.quantity  order by  c.gender ,s.quantity DESC";
    }
    //    age
    else if (n == 2) {
      sql =
          "select c.age, p.product_name, p.product_type, max(s.quantity) as product_quantity from customers as c  inner join orders as o on c.customer_id = o.customer_id inner join sales as s on s.order_id = o.order_id inner join products as p on s.product_id = p.product_id group by c.age, s.quantity  order by  c.age ,s.quantity DESC";
    }
    //    state
    else if (n == 3) {
      sql =
          "select c.state, sum(s.quantity) as product_quantity from customers as c  inner join orders as o on c.customer_id = o.customer_id inner join sales as s on s.order_id = o.order_id inner join products as p on s.product_id = p.product_id group by c.state  order by  c.state ";
    }

    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      ObservableList<Customer> customerList = getDemographicTables(rs, n);
      return customerList;
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("Record not found");
      return null;
    }
  }
}
