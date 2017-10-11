package com.nemesis.spring_hibernate_xml_sample.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GlobalLogger {

    private static Logger rootLogger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static void logDebug(String msg) {
        rootLogger.debug(msg);
    }

}
