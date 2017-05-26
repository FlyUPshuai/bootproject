package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/templates")
public class IndexController {

    @RequestMapping("/index")
    public String  index() throws Exception{
        return "index";
    }

}
