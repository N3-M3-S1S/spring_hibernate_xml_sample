package com.nemesis.spring_hibernate_xml_sample.model.service.logic.driver_service.impl;

import com.nemesis.spring_hibernate_xml_sample.model.service.logic.driver_service.LicenseNumberValidator;
import java.util.regex.Pattern;

public class LicenseNumberValidator_impl implements LicenseNumberValidator {

    private Pattern licenseNumberPattern = Pattern.compile("[0-9]{5}");

    @Override
    public boolean isLicenseNumberValid(String licenseNumber) {
        return licenseNumberPattern.matcher(licenseNumber).matches();
    }

}
