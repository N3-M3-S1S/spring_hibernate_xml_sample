package com.nemesis.spring_hibernate_xml_sample.model.service.logic.driver_service.impl;

import com.nemesis.spring_hibernate_xml_sample.model.service.logic.driver_service.DriverNameValidator;
import java.util.regex.Pattern;

public class DriverNameValidator_impl implements DriverNameValidator{
    private final Pattern driverNamePattern = Pattern.compile("^[a-z\\s]+$", Pattern.CASE_INSENSITIVE);
    
    @Override
    public boolean isDriverNameValid(String driverName) {
        return driverNamePattern.matcher(driverName).matches();
    }
}