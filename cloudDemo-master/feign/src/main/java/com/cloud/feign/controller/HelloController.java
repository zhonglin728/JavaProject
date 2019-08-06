package com.cloud.feign.controller;


import com.cloud.feign.service.HelloService;
import com.cloud.feign.service.inter.IHelloServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private IHelloServiceInter helloService;
    @GetMapping("/hello")
    public String sayHello(String name){
        return helloService.sayHello() + " " + name;
    }
}
