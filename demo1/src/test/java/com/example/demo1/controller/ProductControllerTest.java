package com.example.demo1.controller;

import com.example.demo1.model.Product;
import com.example.demo1.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private ProductService service;
    @Test
    public void selectAll() throws Exception {
        List<Product> products = service.selectAll();
        for (Product p: products
             ) {
            System.out.println(p.getCode());

        }
    }

}