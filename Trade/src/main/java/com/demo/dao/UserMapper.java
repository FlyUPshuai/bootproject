package com.demo.dao;


import com.demo.entity.Page;
import com.demo.entity.User;
import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    public List<User> getUsers();

}
