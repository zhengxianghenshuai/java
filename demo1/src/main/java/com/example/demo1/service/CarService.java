package com.example.demo1.service;

import com.example.demo1.model.Car;

import java.util.List;
import java.util.Map;

public interface CarService {

    //ID查询
    Car selectById(Integer id);
    //插入
    Map<String,Object> insert(Car car);
    //更新
    Map<String ,Object> update(Car car);
    //删除
    Map<String,Object> delete1(Integer id);

    List<Car> selectAll();
}
