package com.example.demo1.controller;

import com.example.demo1.model.Product;
import com.example.demo1.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping("/selectAll")
    @ResponseBody
    public String selectAll(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        JSONObject map = new JSONObject();
        PageHelper.startPage(page -1, limit);
        List<Product> products = service.selectAll();
        PageInfo<Product> pageInfo =  new PageInfo<>(products);
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",products);
        return map.toString();

    }

    @RequestMapping("/del")
    @ResponseBody
    public Map<String,Object> delByid(@RequestParam("id") Integer id ){
        Map<String, Object> map = service.delByid(id);
        return map;
    }


}
