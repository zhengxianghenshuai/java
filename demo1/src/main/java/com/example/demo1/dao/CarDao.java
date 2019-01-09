package com.example.demo1.dao;

import com.example.demo1.model.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarDao {

    Car selectById(Integer id);

    Integer insert(Car car);

    Integer update1(Car car);

    Integer delete1(Integer id);

    List<Car> selectAll();
}
