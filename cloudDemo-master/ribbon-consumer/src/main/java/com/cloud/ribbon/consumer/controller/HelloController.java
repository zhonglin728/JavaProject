package com.cloud.ribbon.consumer.controller;


import com.cloud.ribbon.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;


    @GetMapping("/hello")
    public String sayHello(String name){
        return helloService.sayHello() + " " + name;
    }
    @GetMapping("/findInt")
    public int findInt(@RequestParam(value = "id",required = false,defaultValue = "888") int id){
        return Integer.valueOf(helloService.findInt(id));
    }
    @GetMapping("/findUser")
    public String findUser(String name){
        return "走了走了走！";
    }
}
