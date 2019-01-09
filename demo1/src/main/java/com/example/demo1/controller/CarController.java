package com.example.demo1.controller;

import com.example.demo1.model.Car;
import com.example.demo1.service.CarService;
import com.example.demo1.utils.GetAddress;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService service;

    @RequestMapping("/selectById")
    @ResponseBody
    public Car selectById(@RequestParam("id") Integer id) {
        Car car = service.selectById(id);
        return car;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insert() {
        Car car = new Car();
        car.setCode("Audi-A5");
        car.setName("奥迪A5");
        car.setPrice(500000.00);
        Map<String, Object> map = service.insert(car);
        return map;

    }

    @RequestMapping("/update1")
    @ResponseBody
    public Map<String, Object> update1() {
        Car car = new Car();
        car.setCode("Audi-A8");
        car.setName("奥迪A8L");
        car.setPrice(1000000.00);
        car.setId(7);
        Map<String, Object> update = service.update(car);
        return update;
    }

    @RequestMapping("/delete1")
    @ResponseBody
    public Map<String,Object> delete1(Integer id){
        Map<String, Object> stringObjectMap = service.delete1(id);
        return stringObjectMap;

    }

    @RequestMapping("/select")
    @ResponseBody
    public List<Car> selectAll(){
        List<Car> car = service.selectAll();
        JSONArray jsonArray = JSONArray.fromObject(car);
        System.out.print(jsonArray);

        return jsonArray;
    }
}
