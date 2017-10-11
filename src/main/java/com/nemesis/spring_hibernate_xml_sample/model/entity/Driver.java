package com.nemesis.spring_hibernate_xml_sample.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Driver implements Serializable {

    private String licenseNumber;
    private String name;
    private Car car;
    private Set<Order> orders = new HashSet();

    public Driver(String driverName, String driverLicenseNumber) {
        this.name = driverName;
        this.licenseNumber = driverLicenseNumber;
    }

    public Driver() {
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Driver{" + "driverLicenseNumber=" + licenseNumber + ", driverName=" + name + '}';
    }

}
