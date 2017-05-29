package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.entity.User;
import com.demo.service.UserInfoService;
import org.h2.util.IntArray;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  
@RequestMapping("/user")  
public class UserController {
    @Autowired
    @Qualifier("UserInfoService")
    UserInfoService UserInfoService;




	//用户量
    @RequestMapping(value = "/users" , method= RequestMethod.GET)
    public String users(Integer id) {
        User list=UserInfoService.getUsers(id);
        Map<String,Object> map=new HashMap<String,Object>(1);
        map.put("users",list);
        System.out.print("User:::::"+JSON.toJSONString(map));
        return JSON.toJSONString(map);
    }

//    public static void main(String[] args) {
//    	Page p = new Page();
//    	p.setPage(2);
//    	p.setPageSize(50);
//       System.out.println("装机量"+service.getComputers(5));
//       System.out.println("用户量"+service.getUsers(5));
//       List<User> users = service.getUserList(5, "1", p);
//       System.out.println("总页数："+p.getPageSum());
//       Map<String,Object> map = new HashMap<String,Object>(2);
//       map.put("loginUsers", users);
//       map.put("page", p);
//       System.out.println(JSON.toJSONString(map));
//    }
}
