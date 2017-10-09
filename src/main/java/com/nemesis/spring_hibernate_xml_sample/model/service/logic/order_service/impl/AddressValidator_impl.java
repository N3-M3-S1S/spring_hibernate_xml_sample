package com.nemesis.spring_hibernate_xml_sample.model.service.logic.order_service.impl;

import com.nemesis.spring_hibernate_xml_sample.model.service.logic.order_service.AddressValidator;
import java.util.regex.Pattern;

public class AddressValidator_impl implements AddressValidator{
    private final Pattern ADDRESS_PATTERN = Pattern.compile("^[a-z\\s]+/[0-9]+$", Pattern.CASE_INSENSITIVE);
    
    @Override
    public boolean isAddressValid(String address) {
        return ADDRESS_PATTERN.matcher(address).matches();
    }

}
