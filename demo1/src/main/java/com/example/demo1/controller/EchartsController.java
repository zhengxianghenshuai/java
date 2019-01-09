package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 图形统计表
 */
@Controller
public class EchartsController {

    @RequestMapping("/echarts/getAll")
    @ResponseBody
    public String test(){
        return "";
    }

}
