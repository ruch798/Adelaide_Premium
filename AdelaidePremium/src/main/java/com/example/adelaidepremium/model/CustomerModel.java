package com.example.adelaidepremium.model;

import com.example.adelaidepremium.DatabaseConnection;
import com.example.adelaidepremium.entity.Customer;
import com.example.adelaidepremium.entity.Sales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
  private static Customer getCustomerObject(ResultSet rsSet)
      throws SQLException, ClassNotFoundException {
    try {

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

      return customer;
    } catch (SQLException e) {
      System.out.println("Error occurred while fetching data!");
      e.printStackTrace();
      throw e;
    }
  }

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

  public Customer getCustomerById(int customerId){
    String sql = "select * from customers where customer_id = "+customerId+"";
    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      Customer customer = getCustomerObject(rs);
      return customer;
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("Record not found");
      return null;
    }
  }

  public Customer getCustomerByName(String customerName){
    String sql = "select * from customers where customer_name = "+customerName+"";
    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      Customer customer = getCustomerObject(rs);
      return customer;
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("Record not found");
      return null;
    }
  }

  private static ObservableList<Customer> getDemographicTables(ResultSet rsSet, int n)
          throws SQLException, ClassNotFoundException {
    try {
      ObservableList<Customer> customerList = FXCollections.observableArrayList();

      while (rsSet.next()) {
        if(n == 1){
          Customer customer = new Customer(null, null, null,0);
          customer.setGender(rsSet.getString("gender"));
          customer.setPName(rsSet.getString("product_name"));
          customer.setPType(rsSet.getString("product_type"));
          customer.setPQuantity(rsSet.getInt("product_quantity"));
          customerList.add(customer);
        }
        else if(n == 2){
          Customer customer = new Customer(0, null, null,0);
          customer.setAge(rsSet.getInt("age"));
          customer.setPName(rsSet.getString("product_name"));
          customer.setPType(rsSet.getString("product_type"));
          customer.setPQuantity(rsSet.getInt("product_quantity"));
          customerList.add(customer);
        }
        else if(n == 3){
          Customer customer = new Customer(null,0);
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

  public ObservableList<Customer> getDemographicTablesFromDatabase(int n){
    String sql = null;
    if(n == 1){
      sql = "select c.gender, p.product_name, p.product_type, max(s.quantity) as product_quantity from customers as c  inner join orders as o on c.customer_id = o.customer_id inner join sales as s on s.order_id = o.order_id inner join products as p on s.product_id = p.product_id group by c.gender, s.quantity  order by  c.gender ,s.quantity DESC";
    }
    else if(n == 2){
      sql = "select c.age, p.product_name, p.product_type, max(s.quantity) as product_quantity from customers as c  inner join orders as o on c.customer_id = o.customer_id inner join sales as s on s.order_id = o.order_id inner join products as p on s.product_id = p.product_id group by c.age, s.quantity  order by  c.age ,s.quantity DESC";
    }
    else if(n == 3){
      sql = "select c.state, sum(s.quantity) as product_quantity from customers as c  inner join orders as o on c.customer_id = o.customer_id inner join sales as s on s.order_id = o.order_id inner join products as p on s.product_id = p.product_id group by c.state  order by  c.state ";
    }

    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      ObservableList<Customer> customerList = getDemographicTables(rs,n);
      return customerList;
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("Record not found");
      return null;
    }
  }
}
