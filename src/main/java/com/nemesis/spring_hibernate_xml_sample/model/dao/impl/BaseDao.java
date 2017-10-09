package com.nemesis.spring_hibernate_xml_sample.model.dao.impl;

import com.nemesis.spring_hibernate_xml_sample.model.dao.Dao;
import com.nemesis.spring_hibernate_xml_sample.utils.GlobalLogger;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao<T> implements Dao<T>{
    private final SessionFactory factory;
    private final Class<T> persistentEntityClass;
    
    public BaseDao(SessionFactory factory, Class<T> persistentEntityClass) {
        this.factory = factory;
        this.persistentEntityClass = persistentEntityClass;
    }
   
    protected Session getSession(){
        return factory.getCurrentSession();
    }
    
    protected void beginTransaction(){
        getSession().beginTransaction();
    }
    
    protected void commitTransaction(){
        getSession().getTransaction().commit();
    }
        
    @Override
    public void save(T t){
        GlobalLogger.logDebug("Saving object " + t + " to the database.");
        beginTransaction();
        getSession().persist(t);
        commitTransaction();
        GlobalLogger.logDebug("Object " + t + "is saved.");
    }
    
    
    @Override
    public void delete(T t){
        GlobalLogger.logDebug("Deleting object " + t + " from the database.");
        beginTransaction();
        getSession().delete(t);
        commitTransaction();
        GlobalLogger.logDebug("Object " + t + "is deleted.");
    }
    
    @Override
    public void update(T t) {
        GlobalLogger.logDebug("Updating object " + t + " in the database.");
        beginTransaction();
        getSession().update(t);
        commitTransaction();
        GlobalLogger.logDebug("Object " + t + " is updated.");
    }
    
   
    @Override
    public List<T> getAll(){
        GlobalLogger.logDebug("Getting all objects with type " + persistentEntityClass.getSimpleName() + " from the database");
        beginTransaction();
        CriteriaQuery<T> query = getSession().getCriteriaBuilder().createQuery(persistentEntityClass);
        Root<T> root = query.from(persistentEntityClass);
        List<T> all = getSession().createQuery(query.select(root)).getResultList();
        commitTransaction();
        return all;
    };
        
}
