package com.nemesis.spring_hibernate_xml_sample.model.service.logic.car_service.impl;

import com.nemesis.spring_hibernate_xml_sample.model.service.logic.car_service.CarNumberValidator;
import java.util.regex.Pattern;

public class CarNumberValidator_impl implements CarNumberValidator {

    private Pattern carNumberPattern = Pattern.compile("[0-9]{6}[a-z]{3}", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isCarNumberValid(String carNumber) {
        return carNumberPattern.matcher(carNumber).matches();
    }

}
