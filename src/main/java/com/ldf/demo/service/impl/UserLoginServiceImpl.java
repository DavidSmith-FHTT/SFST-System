package com.ldf.demo.service.impl;

import com.ldf.demo.mapper.UserMapper;
import com.ldf.demo.dto.User;
import com.ldf.demo.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public int add(User user) {
        System.out.println("user  "+user);
        return userMapper.add(user);
    }

    @Override
    public User queryByName(String username) {
        return userMapper.queryByName(username);
    }

    public User queryByEmail(String email){
        return userMapper.queryByEmail(email);
    }

}
