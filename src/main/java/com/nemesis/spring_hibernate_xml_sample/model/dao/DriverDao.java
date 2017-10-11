package com.nemesis.spring_hibernate_xml_sample.model.dao;

import com.nemesis.spring_hibernate_xml_sample.model.entity.Driver;
import java.util.List;

public interface DriverDao extends Dao<Driver> {

    List<Driver> getDriversByName(String name);

    Driver getDriverByLicenseNumber(String licenseNumber);
}
