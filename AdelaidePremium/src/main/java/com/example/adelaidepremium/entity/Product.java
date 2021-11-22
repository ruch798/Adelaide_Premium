package com.example.adelaidepremium.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
  private IntegerProperty idProperty;
  private StringProperty typeProperty;
  private StringProperty nameProperty;
  private StringProperty sizeProperty;
  private StringProperty colourProperty;
  private IntegerProperty priceProperty;
  private IntegerProperty quantityProperty;
  private StringProperty descriptionProperty;

  public Product() {
    this.idProperty = new SimpleIntegerProperty();
    this.typeProperty = new SimpleStringProperty();
    this.nameProperty = new SimpleStringProperty();
    this.sizeProperty = new SimpleStringProperty();
    this.colourProperty = new SimpleStringProperty();
    this.priceProperty = new SimpleIntegerProperty();
    this.quantityProperty = new SimpleIntegerProperty();
    this.descriptionProperty = new SimpleStringProperty();
  }

  public int getId() {
    return idProperty.get();
  }

  public void setId(int id) {
    this.idProperty.set(id);
  }

  public IntegerProperty getProductId() {
    return idProperty;
  }

  public String getType() {
    return typeProperty.get();
  }

  public void setType(String type) {
    this.typeProperty.set(type);
  }

  public StringProperty getProductType() {
    return typeProperty;
  }

  public String getName() {
    return nameProperty.get();
  }

  public void setName(String name) {
    this.nameProperty.set(name);
  }

  public StringProperty getProductName() {
    return nameProperty;
  }

  public String getSize() {
    return sizeProperty.get();
  }

  public void setSize(String size) {
    this.sizeProperty.set(size);
  }

  public StringProperty getProductSize() {
    return sizeProperty;
  }

  public String getColour() {
    return colourProperty.get();
  }

  public void setColour(String colour) {
    this.colourProperty.set(colour);
  }

  public StringProperty getProductColour() {
    return colourProperty;
  }

  public int getPrice() {
    return priceProperty.get();
  }

  public void setPrice(int price) {
    this.priceProperty.set(price);
  }

  public IntegerProperty getProductPrice() {
    return priceProperty;
  }

  public int getQuantity() {
    return quantityProperty.get();
  }

  public void setQuantity(int quantity) {
    this.quantityProperty.set(quantity);
  }

  public IntegerProperty getProductQuantity() {
    return quantityProperty;
  }

  public String getDescription() {
    return descriptionProperty.get();
  }

  public void setDescription(String description) {
    this.descriptionProperty.set(description);
  }

  public StringProperty getProductDescription() {
    return descriptionProperty;
  }
}
