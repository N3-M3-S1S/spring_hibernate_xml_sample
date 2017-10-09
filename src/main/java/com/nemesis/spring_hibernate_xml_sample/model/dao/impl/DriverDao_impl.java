package com.nemesis.spring_hibernate_xml_sample.model.dao.impl;

import com.nemesis.spring_hibernate_xml_sample.model.dao.DriverDao;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Driver;
import com.nemesis.spring_hibernate_xml_sample.utils.GlobalLogger;
import java.util.List;
import org.hibernate.SessionFactory;


public class DriverDao_impl extends BaseDao<Driver> implements DriverDao{

    public DriverDao_impl(SessionFactory factory) {
        super(factory, Driver.class);
    }

    @Override
    public List<Driver> getDriversByName(String name) {
        GlobalLogger.logDebug("Getting drivers with name " + name);
        beginTransaction();
        List driversByName = getSession().createQuery("from Driver where lower(name) like lower(:name)").setParameter("name", "%"+name+"%").getResultList();
        commitTransaction();
        return driversByName;
    }      

    @Override
    public Driver getDriverByLicenseNumber(String licenseNumber) {
        GlobalLogger.logDebug("Getting a driver with license number " + licenseNumber);
        beginTransaction();
        Driver driverByLicenseNumber = getSession().get(Driver.class, licenseNumber);
        commitTransaction();
        return driverByLicenseNumber;
    }

}
