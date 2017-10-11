package com.nemesis.spring_hibernate_xml_sample.model.service.impl;

import com.nemesis.spring_hibernate_xml_sample.model.service.ErrorReportingService;
import com.nemesis.spring_hibernate_xml_sample.model.dao.CarDao;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Car;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Driver;
import com.nemesis.spring_hibernate_xml_sample.model.service.logic.car_service.CarNumberValidator;
import com.nemesis.spring_hibernate_xml_sample.model.service.logic.error_reporter.ErrorReporter;
import com.nemesis.spring_hibernate_xml_sample.model.service.CarService;
import java.util.List;

public class CarService_impl extends ErrorReportingService implements CarService {

    private CarDao carDao;
    private CarNumberValidator carNumberValidator;

    public CarService_impl(CarDao carDao, CarNumberValidator carNumberValidator, ErrorReporter errorReporter) {
        super(errorReporter);
        this.carDao = carDao;
        this.carNumberValidator = carNumberValidator;
    }

    @Override
    public Car createCar(String carModel, String carNumber) {
        if (carNumberValidator.isCarNumberValid(carNumber)) {
            if (getCarByNumber(carNumber) == null) {
                Car c = new Car(carModel, carNumber.toUpperCase());
                carDao.save(c);
                return c;
            } else {
                reportCarWithNumberExists(carNumber);
            }
        } else {
            reportInvalidCarNumberError(carNumber);
        }
        return null;

    }

    @Override
    public void setCarToDriver(Car car, Driver driver) {
        car.setDriver(driver);
        driver.setCar(car);
        carDao.update(car);
    }

    @Override
    public void deleteCar(Car car) {
        carDao.delete(car);
    }

    @Override
    public Car getCarByNumber(String carNumber) {
        if (carNumberValidator.isCarNumberValid(carNumber)) {
            return carDao.getCarByNumber(carNumber);
        } else {
            reportInvalidCarNumberError(carNumber);
            return null;
        }
    }

    @Override
    public List<Car> getCarsByModel(String carModel) {
        return carDao.getCarsByModel(carModel);
    }

    @Override
    public List<Car> getAllCars() {
        return carDao.getAll();
    }

    private void reportInvalidCarNumberError(String carNumber) {
        errorReporter.reportError(String.format("Car number (%s) is not valid. Car number must contain 6 digits and 3 letters", carNumber));
    }

    private void reportCarWithNumberExists(String carNumber) {
        errorReporter.reportError(String.format("Car with number (%s) already exists", carNumber));
    }

}
