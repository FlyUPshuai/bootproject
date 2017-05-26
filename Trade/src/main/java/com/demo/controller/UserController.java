package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.entity.Page;
import com.demo.service.UserInfoService;
import com.demo.service.impl.UserInfoServiceImp;
import org.springframework.web.bind.annotation.*;

import com.demo.entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  
@RequestMapping("/user")  
public class UserController {
    public static UserInfoService service=new UserInfoServiceImp();

    //装机量
	@RequestMapping(value = "/computers" , method= RequestMethod.POST)
    public String computers(@RequestParam int productID) {
        String computers=service.getComputers(productID);
        Map<String,String> map=new HashMap<String,String>(1);
        map.put("computers",computers);

        return JSON.toJSONString(map);
    }

	//用户量
    @RequestMapping(value = "/users" , method= RequestMethod.POST)
    public String users(@RequestParam int productID) {
        String users=service.getUsers(productID);
        Map<String,String> map=new HashMap<String,String>(1);
        map.put("users",users);
        
        return JSON.toJSONString(map);
    }


    @RequestMapping(value = "/loginUsers" , method= RequestMethod.POST)
    public String loginUsers(@RequestParam int productID,
                        @RequestParam int page,
                        @RequestParam String mixTime){
        Page pg=new Page();
        pg.setPage(page);
        pg.setPageSize(50);
        List<User> users=service.getUserList(productID,mixTime,pg);
        Map<String,Object> map = new HashMap<String,Object>(2);
        map.put("loginUsers", users);
        map.put("page", pg);
        
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
