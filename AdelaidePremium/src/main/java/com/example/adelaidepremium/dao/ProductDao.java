package com.example.adelaidepremium.dao;

import com.example.adelaidepremium.entity.Product;
import javafx.collections.ObservableList;

/***
 * Product DAO
 */
public interface ProductDao {
  public ObservableList<Product> getProducts();

  public ObservableList<Product> getProductsStock();

  public Product getProductById(int productId);

  public void decreaseProduct(String productId, int qty);

  public void increaseProduct(String productId, int qty);
}
