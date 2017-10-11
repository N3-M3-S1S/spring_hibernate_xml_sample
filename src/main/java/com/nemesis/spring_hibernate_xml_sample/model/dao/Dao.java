package com.nemesis.spring_hibernate_xml_sample.model.dao;

import java.util.List;

public interface Dao<T> {

    void save(T t);

    void delete(T t);

    void update(T t);

    List<T> getAll();
}
