package com.example.demo1.service.impl;

import com.example.demo1.dao.UserDao;
import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private Map<String,Object> map = new HashMap<>();

    @Override
    public Map<String ,Object> saveUser(User user) {
        Integer integer = userDao.saveUser(user);
        if(integer == 1){
            map.put("key",1);
        }else {
            map.put("key",2);
        }
        return map;
    }

    @Override
    public List<User> selectAll(Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        List<User> userList = userDao.selectAll();
        return userList;
    }

    @Override
    public User selectByPsw(String username, String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        User user = userDao.selectBYpsw(map);
        return user;
    }
}
