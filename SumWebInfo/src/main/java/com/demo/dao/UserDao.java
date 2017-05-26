package com.demo.dao;


import com.demo.entity.Page;
import com.demo.entity.User;
import java.util.List;

public interface UserDao {
    /**
     * 查询装机量
     * @param productID 商品ID
     * @return
     */
    public List<String> getComputers(int productID);

    /**
     * 查询用户量
     * @param productID
     * @return
     */
    public String getUsers(int productID);
	/**
     * 分页查询
     */
    public List<User> getUserList(int productId,String mixTime,Page page);

}
