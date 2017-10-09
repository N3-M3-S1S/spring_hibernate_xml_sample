package com.nemesis.spring_hibernate_xml_sample.model.dao.impl;

import com.nemesis.spring_hibernate_xml_sample.model.dao.OrderDao;
import com.nemesis.spring_hibernate_xml_sample.model.entity.Order;
import com.nemesis.spring_hibernate_xml_sample.utils.GlobalLogger;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;


public class OrderDao_impl extends BaseDao<Order> implements OrderDao{
    
    public OrderDao_impl(SessionFactory factory) {
        super(factory, Order.class);
    }

    @Override
    public List<Order> getOrdesrByFromAdsress(String addressFrom) {
        return getOrdersByAdress(addressFrom, "addressFrom");
    }

    @Override
    public List<Order> getOrdersByToAddress(String addressTo) {
        return getOrdersByAdress(addressTo, "addressTo");
    }
    
    //CriteriaQuery example
    private List<Order> getOrdersByAdress(String address, String direction){
        GlobalLogger.logDebug("Getting all orders with address " + address + " and direction " + direction);
        beginTransaction();
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        Predicate p = cb.like(cb.lower(root.get(direction)), "%"+address+"%".toLowerCase());
        query.where(p);
        List<Order> orders = getSession().createQuery(query.select(root)).getResultList();
        commitTransaction();
        return orders;
    }

   
    

}
