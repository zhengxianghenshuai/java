package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ExportController {



    @RequestMapping("/export")
    @ResponseBody
    public String save(MultipartFile file) {
        return "";
    }
}
