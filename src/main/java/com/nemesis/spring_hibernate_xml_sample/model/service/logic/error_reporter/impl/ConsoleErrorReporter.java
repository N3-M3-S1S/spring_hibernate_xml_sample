package com.nemesis.spring_hibernate_xml_sample.model.service.logic.error_reporter.impl;

import com.nemesis.spring_hibernate_xml_sample.model.service.logic.error_reporter.ErrorReporter;

public class ConsoleErrorReporter implements ErrorReporter {

    @Override
    public void reportError(String errorMessage) {
        System.err.println(errorMessage);
    }

}
