package com.nemesis.spring_hibernate_xml_sample.model.entity;

import java.io.Serializable;

public class Car implements Serializable {

    private String carModel;
    private String carNumber;
    private Driver driver;

    public Car(String carModel, String carNumber) {
        this.carModel = carModel;
        this.carNumber = carNumber;
    }

    public Car() {
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Car{" + "carModel=" + carModel + ", carNumber=" + carNumber + '}';
    }

}
