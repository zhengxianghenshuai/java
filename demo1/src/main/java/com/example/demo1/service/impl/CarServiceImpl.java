package com.example.demo1.service.impl;

import com.example.demo1.dao.CarDao;
import com.example.demo1.model.Car;
import com.example.demo1.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CarDao carDao;

    private Map<String ,Object> map =  new HashMap<>();

    @Override
    public Car selectById(Integer id) {
        Car car = carDao.selectById(id);
        return car;
    }

    @Override
    @Transactional
    public Map<String,Object> insert(Car car) {
        Integer insert = carDao.insert(car);
        if(insert == 1){
            map.put("成功",insert);
        }else{
            map.put("失败",insert);
        }
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> update(Car car) {
        Integer update = carDao.update1(car);
        if (update == 1) {
            map.put("成功", update);
        } else {
            map.put("失败", update);
        }
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> delete1(Integer id) {
        Integer integer = carDao.delete1(id);
        if (integer == 1) {
            map.put("成功", integer);
        } else {
            map.put("失败", integer);
        }
        return map;
    }

    @Override
    public List<Car> selectAll() {
        List<Car> car = carDao.selectAll();
        return car;
    }
}
