package com.nemesis.spring_hibernate_xml_sample.model.dao.impl;

import com.nemesis.spring_hibernate_xml_sample.model.dao.CarDao;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Car;
import com.nemesis.spring_hibernate_xml_sample.utils.GlobalLogger;
import java.util.List;
import org.hibernate.SessionFactory;

public class CarDao_impl extends BaseDao<Car> implements CarDao{

    public CarDao_impl(SessionFactory factory) {
        super(factory, Car.class);
    }

    @Override
    public Car getCarByNumber(String carNumber) {
       GlobalLogger.logDebug("Getting a car with number " + carNumber);
       beginTransaction();
       Car carByNumber = getSession().get(Car.class, carNumber.toUpperCase());
       commitTransaction();
       return carByNumber;
    }

    @Override
    public List<Car> getCarsByModel(String carModel) {
        GlobalLogger.logDebug("Getting cars with model " + carModel);
        beginTransaction();
        List<Car> cars = getSession().createQuery("from Car where lower(carModel) like lower(:model)").setParameter("model", "%"+carModel+"%").getResultList();
        commitTransaction();
        return cars;
    }
   
}