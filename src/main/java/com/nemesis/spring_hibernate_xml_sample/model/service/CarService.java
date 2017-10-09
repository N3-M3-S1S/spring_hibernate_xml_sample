package com.nemesis.spring_hibernate_xml_sample.model.service;

import com.nemesis.spring_hibernate_xml_sample.model.entity.Car;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Driver;
import java.util.List;

public interface CarService {
    Car createCar(String carModel, String carNumber);
    Car getCarByNumber(String carNumber);
    List<Car> getCarsByModel(String carModel);
    List<Car> getAllCars();
    void setCarToDriver(Car car, Driver driver);
    void deleteCar(Car car);
}
