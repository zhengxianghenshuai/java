package com.example.demo1.dao;

import com.example.demo1.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductDao {

    List<Product> selectAll();

    Integer delBYid(Integer id);
}
