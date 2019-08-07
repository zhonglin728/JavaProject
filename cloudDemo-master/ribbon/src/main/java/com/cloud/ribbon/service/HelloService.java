package com.cloud.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Service
public class HelloService {
    public String sayHello(){
        return "helloWorld"; // 提供一个hello World
    }

    public String findInt(int id) {
        return String.valueOf(id);
    }
}
