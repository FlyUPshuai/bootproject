package com.demo.service.impl;

import com.demo.dao.UserMapper;
import com.demo.entity.User;
import com.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserInfoService")
public class UserInfoServiceImp implements UserInfoService{
    @Autowired
    UserMapper userMapper;
    @Override
    public User getUsers(Integer id) {
        User users=userMapper.selectByPrimaryKey(Integer.valueOf(1));
        return users;
    }
}

