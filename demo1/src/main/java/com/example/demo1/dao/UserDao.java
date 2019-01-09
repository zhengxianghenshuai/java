package com.example.demo1.dao;

import com.example.demo1.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    Integer saveUser(User user);

    List<User> selectAll();

    User selectBYpsw(Map<String,Object> map );
}
