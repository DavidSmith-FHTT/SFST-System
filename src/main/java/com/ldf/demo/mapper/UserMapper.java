package com.ldf.demo.mapper;

import com.ldf.demo.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    //查询
    List<User> queryAll();
    //添加数据
    int add(@Param("user") User user);
    //根据用户名查询数据
    User queryByName(String username);
    //根据用户邮箱查询数据
    User queryByEmail(String email);

}
