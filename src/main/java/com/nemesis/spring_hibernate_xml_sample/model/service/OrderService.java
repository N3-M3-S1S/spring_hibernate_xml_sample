package com.nemesis.spring_hibernate_xml_sample.model.service;

import com.nemesis.spring_hibernate_xml_sample.model.entity.Driver;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(String addressFrom, String addressTo);
    List<Order> getAllOrdersByFromAddress(String addressFrom);
    List<Order> getAllOrdersByToAddress(String addressTo);
    List<Order> getAllOrders();
    void setOrderToDriver(Driver driver, Order order);
    void deleteOrder(Order order);
}
