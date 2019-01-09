package com.example.demo1.controller;

import com.example.demo1.utils.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Controller
public class ImagController {

    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    @ResponseBody
    public String method(MultipartFile file, HttpServletRequest request){
        try {
            UploadUtils.uploadFile(file,request);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
