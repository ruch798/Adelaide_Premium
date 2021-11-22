package com.example.adelaidepremium.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {
    private IntegerProperty order_idProperty;
    private IntegerProperty customer_idProperty;
    private IntegerProperty paymentProperty;
    private StringProperty order_dateProperty;
    private StringProperty delivery_dateProperty;

    public Order() {
        this.order_idProperty = new SimpleIntegerProperty();
        this.customer_idProperty = new SimpleIntegerProperty();
        this.paymentProperty = new SimpleIntegerProperty();
        this.order_dateProperty = new SimpleStringProperty();
        this.delivery_dateProperty = new SimpleStringProperty();
    }

    public int getId() {
        return order_idProperty.get();
    }

    public void setId(int id) {
        this.order_idProperty.set(id);
    }

    public IntegerProperty getOrderId() {
        return order_idProperty;
    }

    public int getCustomerId() {
        return customer_idProperty.get();
    }

    public void setCustomerId(int id) {
        this.customer_idProperty.set(id);
    }

    public IntegerProperty getOrderCustomerId() {
        return customer_idProperty;
    }

    public int getPayment() {
        return paymentProperty.get();
    }

    public void setPayment(int id) {
        this.paymentProperty.set(id);
    }

    public IntegerProperty getOrderPayment() {
        return paymentProperty;
    }

    public String getDate() {
        return order_dateProperty.get();
    }

    public void setDate(String orderDate) {
        this.order_dateProperty.set(orderDate);
    }

    public StringProperty getOrderDate() {
        return order_dateProperty;
    }

    public String getDeliveryDate() {
        return delivery_dateProperty.get();
    }

    public void setDeliveryDate(String deliveryDate) {
        this.delivery_dateProperty.set(deliveryDate);
    }

    public StringProperty getOrderDeliveryDate() {
        return delivery_dateProperty;
    }


}
