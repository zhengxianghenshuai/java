package com.example.demo1.controller;

import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import com.example.demo1.utils.UploadFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;




    @RequestMapping("/save")
    @ResponseBody
    public Map<String,Object> saveUser(@Param("user") User user){
        Map<String, Object> map = service.saveUser(user);
        return map;
    }

    @RequestMapping("/select")
    @ResponseBody
    public String select(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        JSONObject map = new JSONObject();
        PageHelper.startPage(page -1, limit);
        List<User> userList = service.selectAll(page,limit);
        //设置返回的总记录数
        PageInfo<User> pageInfo=new PageInfo<>(userList);
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",userList);
        return map.toString();
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile file1, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashMap<>();
        String image = UploadFile.upload(file1,request,response);
        map.put("code",0);
        map.put("image",image);
        return map;
    }


    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(@Param("jsonData") User user,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User users = service.selectByPsw(user.getUsername(), user.getPassword());
        if(users != null){
            map.put("status","ok");
            map.put("text","登录成功");
            HttpSession session = request.getSession();
            session.setAttribute("username",users.getUsername());
        }else{
            map.put("erro","账号或密码错误");
        }
        return map;
    }


}


