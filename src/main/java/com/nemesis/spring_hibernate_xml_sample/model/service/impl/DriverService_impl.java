package com.nemesis.spring_hibernate_xml_sample.model.service.impl;

import com.nemesis.spring_hibernate_xml_sample.model.dao.DriverDao;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Driver;
import com.nemesis.spring_hibernate_xml_sample.model.service.DriverService;
import com.nemesis.spring_hibernate_xml_sample.model.service.ErrorReportingService;
import com.nemesis.spring_hibernate_xml_sample.model.service.logic.driver_service.DriverNameValidator;
import com.nemesis.spring_hibernate_xml_sample.model.service.logic.driver_service.LicenseNumberValidator;
import com.nemesis.spring_hibernate_xml_sample.model.service.logic.error_reporter.ErrorReporter;
import java.util.List;

public class DriverService_impl extends ErrorReportingService implements DriverService {

    private DriverDao driverDao;
    private DriverNameValidator driverNameValidator;
    private LicenseNumberValidator licenseNumberValidator;

    public DriverService_impl(DriverDao driverDao, DriverNameValidator driverNameValidator,
            LicenseNumberValidator licenseNumberValidator, ErrorReporter errorReporter) {
        super(errorReporter);
        this.driverDao = driverDao;
        this.driverNameValidator = driverNameValidator;
        this.licenseNumberValidator = licenseNumberValidator;
        this.errorReporter = errorReporter;
    }

    @Override
    public Driver createDriver(String driverName, String licenseNumber) {
        if (driverNameValidator.isDriverNameValid(driverName)) {
            if (licenseNumberValidator.isLicenseNumberValid(licenseNumber)) {
                if (driverDao.getDriverByLicenseNumber(licenseNumber) == null) {
                    Driver driver = new Driver(driverName, licenseNumber);
                    driverDao.save(driver);
                    return driver;
                } else {
                    reportLicenseNumberExistsError(licenseNumber);
                }
            } else {
                reportInvalidLicenseNumberError(licenseNumber);
            }
        } else {
            reportInvalidDriverNameError(driverName);
        }
        return null;
    }

    @Override
    public Driver getDriverByLicenseNumber(String driverLicenseNumber) {
        if (licenseNumberValidator.isLicenseNumberValid(driverLicenseNumber)) {
            return driverDao.getDriverByLicenseNumber(driverLicenseNumber);
        } else {
            reportInvalidLicenseNumberError(driverLicenseNumber);
            return null;
        }
    }

    @Override
    public void deleteDriver(Driver d) {
        driverDao.delete(d);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverDao.getAll();
    }

    @Override
    public List<Driver> getDriversByName(String driverName) {
        if (driverNameValidator.isDriverNameValid(driverName)) {
            return driverDao.getDriversByName(driverName);
        } else {
            reportInvalidDriverNameError(driverName);
            return null;
        }
    }

    private void reportInvalidDriverNameError(String driverName) {
        errorReporter.reportError(String.format("Driver name %s is not valid. Driver name must contain only letters and spaces.", driverName));
    }

    private void reportLicenseNumberExistsError(String licenseNumber) {
        errorReporter.reportError(String.format("Driver with license number (%s) already exists.", licenseNumber));
    }

    private void reportInvalidLicenseNumberError(String licenseNumber) {
        errorReporter.reportError(String.format("License number %s is not valid. License number must contain 5 digits.", licenseNumber));
    }

}
