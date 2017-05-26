package com.demo.service;


import com.demo.entity.Page;
import com.demo.entity.User;
import java.util.List;

public interface UserInfoService {
    public String getComputers(int productID);
    
    public String getUsers(int productID);
	public List<User> getUserList(int productId,String mixTime,Page page);
}
