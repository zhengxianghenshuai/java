package com.example.demo1.service;

import com.example.demo1.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String ,Object> saveUser(User user);

    List<User> selectAll(Integer page, Integer limit);

    User selectByPsw(String username,String password);
}
