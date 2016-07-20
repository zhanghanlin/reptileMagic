package com.demo.java.base.service;

import com.demo.java.common.vo.TablePage;
import com.demo.java.base.dao.CarMapper;
import com.demo.java.base.entity.Car;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CarService {

    @Resource
    CarMapper carMapper;

    public Car get(String id) {
        return carMapper.get(id);
    }

    public int insert(Car car) {
        return carMapper.insert(car);
    }

    public int update(Car car) {
        car.setUpdateTime(new Date());
        return carMapper.update(car);
    }

    public int saveOrUpdate(Car car) {
        if (get(car.getId()) != null) {
            return update(car);
        } else {
            return insert(car);
        }
    }

    public TablePage<Car> selectByPage(int start, int size) {
        List<Car> list = carMapper.selectByPage(start, size);
        int total = carMapper.getTotalCount();
        return new TablePage<>(1, total, total, list);
    }
}
