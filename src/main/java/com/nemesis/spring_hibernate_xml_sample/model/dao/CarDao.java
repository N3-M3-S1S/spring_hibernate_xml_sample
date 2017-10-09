package com.nemesis.spring_hibernate_xml_sample.model.dao;

import com.nemesis.spring_hibernate_xml_sample.model.entity.Car;
import java.util.List;

public interface CarDao extends Dao<Car>{
    Car getCarByNumber(String carNumber);
    List<Car> getCarsByModel(String carModel);
}
