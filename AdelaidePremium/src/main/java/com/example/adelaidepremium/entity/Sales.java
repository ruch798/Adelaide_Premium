package com.example.adelaidepremium.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sales {
  private IntegerProperty sales_idProperty;
  private IntegerProperty order_idProperty;
  private IntegerProperty product_idProperty;
  private IntegerProperty priceProperty;
  private IntegerProperty quantityProperty;
  private IntegerProperty totalPriceProperty;
  private StringProperty salesMonthProperty;
  private IntegerProperty totalSalesProperty;
  private StringProperty productTypeProperty;
  private StringProperty productNameProperty;

  public Sales() {
    this.sales_idProperty = new SimpleIntegerProperty();
    this.order_idProperty = new SimpleIntegerProperty();
    this.product_idProperty = new SimpleIntegerProperty();
    this.priceProperty = new SimpleIntegerProperty();
    this.quantityProperty = new SimpleIntegerProperty();
    this.totalPriceProperty = new SimpleIntegerProperty();
  }

  public Sales(String sales_month, int total_sales){
    this.salesMonthProperty = new SimpleStringProperty();
    this.totalSalesProperty = new SimpleIntegerProperty();
  }

  public Sales(String product_type, String product_name, int quantity){
    this.productTypeProperty = new SimpleStringProperty();
    this.productNameProperty = new SimpleStringProperty();
    this.quantityProperty = new SimpleIntegerProperty();
  }

  public int getId() {
    return sales_idProperty.get();
  }

  public IntegerProperty getSalesId() {
    return sales_idProperty;
  }

  public void setId(int id) {
    this.sales_idProperty.set(id);
  }

  public int getProductId() {
    return product_idProperty.get();
  }

  public IntegerProperty getSalesProductId() {
    return product_idProperty;
  }

  public void setProductId(int product_id) {
    this.product_idProperty.set(product_id);
  }

  public int getOrderId() {
    return order_idProperty.get();
  }

  public IntegerProperty getSalesOrderId() {
    return order_idProperty;
  }

  public void setOrderId(int order_id) {
    this.order_idProperty.set(order_id);
  }

  public int getPrice() {
    return priceProperty.get();
  }

  public void setPrice(int price) {
    this.priceProperty.set(price);
  }

  public IntegerProperty getSalesPrice() {
    return priceProperty;
  }

  public int getQuantity() {
    return quantityProperty.get();
  }

  public void setQuantity(int quantity) {
    this.quantityProperty.set(quantity);
  }

  public IntegerProperty getSalesQuantity() {
    return quantityProperty;
  }

  public int getTotalPrice() {
    return totalPriceProperty.get();
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPriceProperty.set(totalPrice);
  }

  public IntegerProperty getSalesTotalPrice() {
    return totalPriceProperty;
  }

  public String getMonth() {
    return salesMonthProperty.get();
  }

  public StringProperty getSalesMonth() {
    return salesMonthProperty;
  }

  public void setMonth(String month) {
    this.salesMonthProperty.set(month);
  }

  public int getTotalSales() {
    return totalSalesProperty.get();
  }

  public void setTotalSales(int totalPrice) {
    this.totalSalesProperty.set(totalPrice);
  }

  public IntegerProperty getSalesTotalSales() {
    return totalSalesProperty;
  }

  public String getProdType() { return productTypeProperty.get();}

  public StringProperty getProductType() {
    return productTypeProperty;
  }

  public void setProdType(String prodType) { this.productTypeProperty.set(prodType);}

  public String getProdName() { return productNameProperty.get();}

  public StringProperty getProductName() {
    return productNameProperty;
  }

  public void setProdName(String prodName) {
    this.productNameProperty.set(prodName);
  }

}
