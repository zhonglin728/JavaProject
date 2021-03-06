package com.cloud.ribbon.controller;


import com.cloud.ribbon.service.HelloService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;
    @GetMapping("/hello")
    public String sayHello(String name){
        return helloService.sayHello() + " " + name;
    }
    @GetMapping("/findInt")
    public String findInt(@RequestParam(value = "id" ,defaultValue = "88",required = false) int id){
        return helloService.findInt(id);
    }
}
