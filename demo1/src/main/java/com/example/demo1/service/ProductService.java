package com.example.demo1.service;

import com.example.demo1.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> selectAll();

    Map<String,Object> delByid( Integer id);
}
