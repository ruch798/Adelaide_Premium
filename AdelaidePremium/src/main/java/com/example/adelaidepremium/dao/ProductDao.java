package com.example.adelaidepremium.dao;

import com.example.adelaidepremium.entity.Product;
import javafx.collections.ObservableList;

public interface ProductDao {
        public ObservableList<Product> getProducts();
        public Product getProductById(int productId);
        public Product getProductByName(String productName);
        public void decreaseProduct(String productId, int qty);
        public void increaseProduct(String productId, int qty);
}

