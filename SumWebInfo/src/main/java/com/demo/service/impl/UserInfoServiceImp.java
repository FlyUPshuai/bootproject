package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.dao.impl.UserDaoImp;
import com.demo.entity.Page;
import com.demo.entity.User;
import com.demo.service.UserInfoService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UserInfoServiceImp implements UserInfoService{


    public UserDao dao=new UserDaoImp();
    @Override
    public String getComputers(int productID) {
       List<String> list=dao.getComputers(productID);
        Set<String> set=new HashSet<String>();
        set.addAll(list);

        return set.size()+"";
    }
    @Override
    public String getUsers(int productID) {

    	return dao.getUsers(productID);
    	
    }
	
	@Override
    public List<User> getUserList(int productId, String mixTime, Page page) {
        List<User> users=dao.getUserList(productId,mixTime,page);
        return users;
    }
}
