package com.example.adelaidepremium.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/***
 * Customer Entity
 */
public class Customer {

  private IntegerProperty idProperty;
  private StringProperty nameProperty;
  private StringProperty genderProperty;
  private IntegerProperty ageProperty;
  private StringProperty homeAddressProperty;
  private IntegerProperty zipCodeProperty;
  private StringProperty cityProperty;
  private StringProperty stateProperty;
  private StringProperty countryProperty;
  private StringProperty productNameProperty;
  private StringProperty productTypeProperty;
  private IntegerProperty productQuantityProperty;

  /***
   * Customer default constructor
   */
  public Customer() {
    this.idProperty = new SimpleIntegerProperty();
    this.nameProperty = new SimpleStringProperty();
    this.genderProperty = new SimpleStringProperty();
    this.ageProperty = new SimpleIntegerProperty();
    this.homeAddressProperty = new SimpleStringProperty();
    this.zipCodeProperty = new SimpleIntegerProperty();
    this.cityProperty = new SimpleStringProperty();
    this.stateProperty = new SimpleStringProperty();
    this.countryProperty = new SimpleStringProperty();
  }

  /***
   * Customer demographic constructor
   */
  public Customer(String gender, String product_name, String product_type, int product_quantity) {
    this.genderProperty = new SimpleStringProperty();
    this.productNameProperty = new SimpleStringProperty();
    this.productTypeProperty = new SimpleStringProperty();
    this.productQuantityProperty = new SimpleIntegerProperty();
  }

  /***
   * Customer demographic constructor
   */
  public Customer(int age, String product_name, String product_type, int product_quantity) {
    this.ageProperty = new SimpleIntegerProperty();
    this.productNameProperty = new SimpleStringProperty();
    this.productTypeProperty = new SimpleStringProperty();
    this.productQuantityProperty = new SimpleIntegerProperty();
  }

  /***
   * Customer demographic constructor
   */
  public Customer(String state, int product_quantity) {
    this.stateProperty = new SimpleStringProperty();
    this.productQuantityProperty = new SimpleIntegerProperty();
  }

  //  getter and setter methods
  public int getId() {
    return idProperty.get();
  }

  public void setId(int id) {
    this.idProperty.set(id);
  }

  public IntegerProperty getCustomerId() {
    return idProperty;
  }

  public String getName() {
    return nameProperty.get();
  }

  public void setName(String name) {
    this.nameProperty.set(name);
  }

  public StringProperty getCustomerName() {
    return nameProperty;
  }

  public String getGender() {
    return genderProperty.get();
  }

  public void setGender(String gender) {
    this.genderProperty.set(gender);
  }

  public StringProperty getCustomerGender() {
    return genderProperty;
  }

  public int getAge() {
    return ageProperty.get();
  }

  public void setAge(int age) {
    this.ageProperty.set(age);
  }

  public IntegerProperty getCustomerAge() {
    return ageProperty;
  }

  public int getZipCode() {
    return zipCodeProperty.get();
  }

  public void setZipCode(int zipCode) {
    this.zipCodeProperty.set(zipCode);
  }

  public IntegerProperty getCustomerZipCode() {
    return zipCodeProperty;
  }

  public String getHomeAddress() {
    return homeAddressProperty.get();
  }

  public void setHomeAddress(String homeAddress) {
    this.homeAddressProperty.set(homeAddress);
  }

  public StringProperty getCustomerHomeAddress() {
    return homeAddressProperty;
  }

  public String getCity() {
    return cityProperty.get();
  }

  public void setCity(String city) {
    this.cityProperty.set(city);
  }

  public StringProperty getCustomerCity() {
    return cityProperty;
  }

  public String getState() {
    return stateProperty.get();
  }

  public void setState(String state) {
    this.stateProperty.set(state);
  }

  public StringProperty getCustomerState() {
    return stateProperty;
  }

  public String getCountry() {
    return countryProperty.get();
  }

  public void setCountry(String country) {
    this.countryProperty.set(country);
  }

  public StringProperty getCustomerCountry() {
    return countryProperty;
  }

  public int getPQuantity() {
    return productQuantityProperty.get();
  }

  public void setPQuantity(int quantity) {
    this.productQuantityProperty.set(quantity);
  }

  public IntegerProperty getProductQuantity() {
    return productQuantityProperty;
  }

  public String getPType() {
    return productTypeProperty.get();
  }

  public void setPType(String type) {
    this.productTypeProperty.set(type);
  }

  public StringProperty getProductType() {
    return productTypeProperty;
  }

  public String getPName() {
    return productNameProperty.get();
  }

  public void setPName(String pname) {
    this.productNameProperty.set(pname);
  }

  public StringProperty getProductName() {
    return productNameProperty;
  }
}
