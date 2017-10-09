package com.nemesis.spring_hibernate_xml_sample.model.entity;

import java.io.Serializable;

public class Order implements Serializable{
    private int orderID;
    private Driver driver;
    private String addressFrom;
    private String addressTo;

    public Order(String addressFrom, String addressTo) {
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
    }
    
    public Order(){}

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", addressFrom=" + addressFrom + ", addressTo=" + addressTo + '}';
    }
      
}
