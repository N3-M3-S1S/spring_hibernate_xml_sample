package com.nemesis.spring_hibernate_xml_sample.model.service.impl;

import com.nemesis.spring_hibernate_xml_sample.model.dao.OrderDao;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Driver;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Order;
import com.nemesis.spring_hibernate_xml_sample.model.service.logic.error_reporter.ErrorReporter;
import com.nemesis.spring_hibernate_xml_sample.model.service.logic.order_service.AddressValidator;
import com.nemesis.spring_hibernate_xml_sample.model.service.ErrorReportingService;
import com.nemesis.spring_hibernate_xml_sample.model.service.OrderService;
import java.util.List;

public class OrderService_impl extends ErrorReportingService implements OrderService {

    private OrderDao orderDao;
    private AddressValidator addressValidator;

    public OrderService_impl(OrderDao orderDao, AddressValidator addressValidator, ErrorReporter errorReporter) {
        super(errorReporter);
        this.orderDao = orderDao;
        this.addressValidator = addressValidator;
    }

    @Override
    public Order createOrder(String addressFrom, String addressTo) {
        if (addressValidator.isAddressValid(addressFrom)) {
            if (addressValidator.isAddressValid(addressTo)) {
                Order order = new Order(addressFrom, addressTo);
                orderDao.save(order);
                return order;
            } else {
                reportInvalidAddress(addressTo);
            }
        } else {
            reportInvalidAddress(addressFrom);
        }
        return null;

    }

    @Override
    public void deleteOrder(Order order) {
        orderDao.delete(order);
    }

    @Override
    public List<Order> getAllOrdersByFromAddress(String addressFrom) {
        return orderDao.getOrdesrByFromAdsress(addressFrom);

    }

    @Override
    public List<Order> getAllOrdersByToAddress(String addressTo) {
        return orderDao.getOrdersByToAddress(addressTo);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    private void reportInvalidAddress(String address) {
        errorReporter.reportError(String.format("Invalid address %s. Address must be in format STREET_NAME/STREET_NUMBER", address));
    }

    @Override
    public void setOrderToDriver(Driver driver, Order order) {
        driver.getOrders().add(order);
        order.setDriver(driver);
        orderDao.update(order);
    }
}
