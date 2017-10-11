package com.nemesis.spring_hibernate_xml_sample.model.service;

import com.nemesis.spring_hibernate_xml_sample.model.service.logic.error_reporter.ErrorReporter;

public abstract class ErrorReportingService {

    protected ErrorReporter errorReporter;

    public ErrorReportingService(ErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }

}
