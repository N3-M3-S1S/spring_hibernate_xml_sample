package com.nemesis.spring_hibernate_xml_sample.model.dao;

import com.nemesis.spring_hibernate_xml_sample.model.entity.Order;
import java.util.List;

public interface OrderDao extends Dao<Order> {

    List<Order> getOrdesrByFromAdsress(String fromAdress);

    List<Order> getOrdersByToAddress(String toAdress);
}
