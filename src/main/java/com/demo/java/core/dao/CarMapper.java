package com.demo.java.core.dao;

import com.demo.java.core.entity.Car;

import java.util.List;

public interface CarMapper {

    int delete(String id);

    int insert(Car record);

    Car get(String id);

    int update(Car record);

    List<Car> selectByPage(int start, int size);

    int getTotalCount();
}