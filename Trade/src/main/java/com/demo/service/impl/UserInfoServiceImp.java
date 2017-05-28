package com.demo.service.impl;

import com.demo.dao.UserMapper;
import com.demo.entity.User;
import com.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("UserInfoService")
public class UserInfoServiceImp implements UserInfoService{
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getUsers() {
        List<User> users=userMapper.getUsers();
        return users;
    }
}
