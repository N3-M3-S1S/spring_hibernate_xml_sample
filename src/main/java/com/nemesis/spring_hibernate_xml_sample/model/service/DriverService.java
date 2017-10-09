package com.nemesis.spring_hibernate_xml_sample.model.service;

import com.nemesis.spring_hibernate_xml_sample.model.entity.Driver;
import java.util.List;

public interface DriverService {
    Driver createDriver(String driverName, String driverLicenseNumber);
    Driver getDriverByLicenseNumber(String driverLicenseNumber);
    List<Driver> getAllDrivers();
    List<Driver> getDriversByName(String driverName);
    void deleteDriver(Driver d);
}
