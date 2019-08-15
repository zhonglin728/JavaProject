package com.cloud.ribbon.consumer.controller;


import com.cloud.ribbon.consumer.entity.User;
import com.cloud.ribbon.consumer.service.HelloService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;


    @GetMapping("/hello")
    public String sayHello(String name){
        return helloService.sayHello() + " " + name;
    }

    @GetMapping("/findInt")
    public int findInt(@RequestParam(name = "id",required = false,defaultValue = "888") int id){
        return Integer.valueOf(helloService.findInt(id));
    }

    @GetMapping("/findUser")
    public String findUser(String name){
        return "走了走了走！";
    }

    @PostMapping("/saveUser")
    public Map saveUser(
            @RequestParam(name = "id",required = false,defaultValue = "0") String id,
            @RequestParam(name = "userName",required = false) String userName,
            @RequestParam(name = "userPassword") String userPassword
            ) throws IOException {
        Map map = new HashMap();
        map.put("id",id);
        map.put("userName",userName);
        map.put("userPassword",userPassword);
        return map;
    }

    @PostMapping("/saveUserEntity")
    public Map saveUserEntity(@RequestBody User user){
        user.setUserName(user.getUserName()+ new Random().toString());
        Map map = new ObjectMapper().convertValue(user, Map.class);
        return map;
    }
}
