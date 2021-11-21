package com.scy.controller;


import com.scy.model.User;
import com.scy.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
public class TestController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/test")
    public String testController(){
//        int result=10/0;
        return "hello";
    }

    @RequestMapping("/queryList")
//    @CrossOrigin
    public List<User> queryUserList(){
//        System.out.println(user);
        List<User> list=userService.list();
        return list;
    }


    @RequestMapping("user/queryAll")
//    @CrossOrigin
    public List<User> queryListAll(@RequestBody User user){
        System.out.println(user);
        List<User> list=userService.list();
        return list;
    }


}
