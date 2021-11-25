package com.example.adelaidepremium.model;

import com.example.adelaidepremium.DatabaseConnection;
import com.example.adelaidepremium.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * Product Model
 */
public class ProductModel {

  /***
   * get product object
   * @param rsSet
   * @return product object
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  private static Product getProductObject(ResultSet rsSet)
      throws SQLException, ClassNotFoundException {
    try {
      Product product = new Product();
      while (rsSet.next()) {
        product.setId(rsSet.getInt("product_id"));
        product.setType(rsSet.getString("product_type"));
        product.setName(rsSet.getString("product_name"));
        product.setSize(rsSet.getString("size"));
        product.setColour(rsSet.getString("colour"));
        product.setPrice(rsSet.getInt("price"));
        product.setQuantity(rsSet.getInt("quantity"));
        product.setDescription(rsSet.getString("description"));
      }
      return product;
    } catch (SQLException e) {
      System.out.println("Error occurred while fetching data!");
      e.printStackTrace();
      throw e;
    }
  }

  /***
   * get product objects
   * @param rsSet
   * @return list of products
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  private static ObservableList<Product> getProductObjects(ResultSet rsSet)
      throws SQLException, ClassNotFoundException {
    try {
      ObservableList<Product> productlist = FXCollections.observableArrayList();

      while (rsSet.next()) {
        Product product = new Product();
        product.setId(rsSet.getInt("product_id"));
        product.setType(rsSet.getString("product_type"));
        product.setName(rsSet.getString("product_name"));
        product.setSize(rsSet.getString("size"));
        product.setColour(rsSet.getString("colour"));
        product.setPrice(rsSet.getInt("price"));
        product.setQuantity(rsSet.getInt("quantity"));
        product.setDescription(rsSet.getString("description"));
        productlist.add(product);
      }
      return productlist;
    } catch (SQLException e) {
      System.out.println("Error occurred while fetching data!");
      e.printStackTrace();
      return null;
    }
  }

  /***
   * get product stock
   * @param rsSet
   * @return stock of products
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  private static ObservableList<Product> getProductStock(ResultSet rsSet)
      throws SQLException, ClassNotFoundException {
    try {
      ObservableList<Product> productlist = FXCollections.observableArrayList();

      while (rsSet.next()) {
        Product product = new Product();
        product.setType(rsSet.getString("product_type"));
        product.setName(rsSet.getString("product_name"));
        product.setQuantity(rsSet.getInt("quantity"));
        product.setDescription(rsSet.getString("description_example"));
        productlist.add(product);
      }
      return productlist;
    } catch (SQLException e) {
      System.out.println("Error occurred while fetching data!");
      e.printStackTrace();
      return null;
    }
  }

  /***
   * get products
   * @return list of products
   */
  public ObservableList<Product> getProducts() {
    String sql = "select * from products";
    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      ObservableList<Product> productlist = getProductObjects(rs);
      return productlist;
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /***
   * get products stock
   * @return list of products stock
   */
  public ObservableList<Product> getProductsStock() {
    String sql =
        "select product_type, product_name, sum(quantity) AS quantity, description AS description_example from products group by product_type, product_name order by product_type, sum(quantity) desc";
    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      ObservableList<Product> productlist = getProductStock(rs);
      return productlist;
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /***
   * get product by id
   * @param productId
   * @return product object
   */
  public Product getProductById(int productId) {
    String sql = "select * from products where product_ID =" + productId + "";
    try {
      ResultSet rs = DatabaseConnection.database_execute(sql);
      Product product = getProductObject(rs);
      return product;
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("Record not found");
      return null;
    }
  }

  /***
   * insert new product into database
   * @param p
   */
  public void addNewProduct(Product p) {

    String sql =
        "INSERT INTO products(product_ID,product_type,product_name, size, colour, price, quantity, description) VALUES("
            + p.getId()
            + ",\""
            + p.getType()
            + "\",\""
            + p.getName()
            + "\",\""
            + p.getSize()
            + "\",\""
            + p.getColour()
            + "\","
            + p.getPrice()
            + ","
            + p.getQuantity()
            + ",\""
            + p.getDescription()
            + "\")";

    try {
      DatabaseConnection.database_executeQuery(sql);

    } catch (SQLException | ClassNotFoundException e) {
      System.out.println("Error occured while adding the record");
      e.printStackTrace();
    }
  }

  /***
   * decrease product quantity
   * @param productId
   * @param qty
   */
  public void decreaseProduct(int productId, int qty) {
    String sql =
        "update products set quantity = quantity - '"
            + qty
            + "' where product_ID = "
            + productId
            + "";

    try {
      DatabaseConnection.database_executeQuery(sql);

    } catch (SQLException | ClassNotFoundException e) {
      System.out.println("Error occured while updating the record");
      e.printStackTrace();
    }
  }

  /***
   * increase product quantity
   * @param productId
   * @param qty
   */
  public void increaseProduct(int productId, int qty) {
    String sql =
        "update products set quantity = quantity + '"
            + qty
            + "' where product_ID = "
            + productId
            + "";

    try {
      DatabaseConnection.database_executeQuery(sql);

    } catch (SQLException | ClassNotFoundException e) {
      System.out.println("Error occured while updating the record");
      e.printStackTrace();
    }
  }
}
