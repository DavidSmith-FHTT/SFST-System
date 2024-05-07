package com.ldf.demo.service;

import com.ldf.demo.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserLoginService {

    //查询
    public List<User> queryAll();
    //添加数据
    public int add(User user);
    //根据用户名查询数据
    public User queryByName(String username);
    //根据用户邮箱查询数据
    public User queryByEmail(String email);
}
