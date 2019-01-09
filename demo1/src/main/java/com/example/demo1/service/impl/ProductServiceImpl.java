package com.example.demo1.service.impl;

import com.example.demo1.dao.ProductDao;
import com.example.demo1.model.Product;
import com.example.demo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    private Map<String,Object> map = new HashMap<>();
    @Override
    public List<Product> selectAll() {
        List<Product> products = productDao.selectAll();
        return products;
    }

    @Override
    @Transactional
    public Map<String, Object> delByid(Integer id) {
        Integer integer = productDao.delBYid(id);
        if(integer == 1){
            map.put("state",1);
        }else{
            map.put("state",2);
        }
        return map;
    }
}
