package com.cloud.ribbon.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate; //注入resttemplate
    public String sayHello(){
        //通过rest调用 调用provider服务
        return restTemplate.getForObject("http://ribbon-provider/hello?name=zhangtaifeng",String.class);
    }

    public String findInt(int id) {
        //通过rest调用 调用provider服务
        Map map = new HashMap();
        map.put("id",id);
        return restTemplate.getForObject("http://ribbon-provider/findInt?id={id}",String.class,map);
    }
}
