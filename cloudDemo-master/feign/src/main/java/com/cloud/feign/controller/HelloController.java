package com.cloud.feign.controller;


import com.cloud.feign.entity.User;
import com.cloud.feign.service.inter.IHelloServiceInter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
@RestController
public class HelloController {
    @Autowired
    private IHelloServiceInter helloService;

    @GetMapping("/hello")
    public String sayHello(String name) {
        return helloService.sayHello() + " " + name;
    }

    @GetMapping("/findInt")
    public int findInt(@RequestParam(value = "id" , required = false, defaultValue = "123") int id) {
        return helloService.findInt(id);
    }

    @GetMapping(name = "获取Gitgub信息！" , value = {"gitHubApi" , "gitHub"})
    public ResponseEntity<Map> gitHubApi(@RequestParam(value = "q") String qq) {
        Map map = new HashMap(16);
        map.put("data" , helloService.gitHubApi(qq));
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping(value = {"getLayApi" , "getLayApis"})
    public Map getLayApi(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) {
        return helloService.getLayApi(page, limit);
    }


    @PostMapping("/saveUser")
    public Map saveUser(
            @RequestParam(name = "id" , required = false, defaultValue = "0") String id,
            @RequestParam(name = "userName" , required = false) String userName,
            @RequestParam(name = "userPassword") String userPassword
    ) {

        return helloService.saveUser(id, userName, userPassword);

    }

    @PostMapping("saveUserEntity")
    public Map saveUserEntity(@RequestBody User user, HttpServletRequest request) throws IOException {
        //User user1 = new ObjectMapper().readValue(request.getReader(), User.class);
        return helloService.saveUserEntity(user);

    }
}
