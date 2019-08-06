package com.cloud.feign.service;

import com.cloud.feign.rpc.GetHello;
import com.cloud.feign.service.inter.IHelloServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService implements IHelloServiceInter {
    @Autowired
    private GetHello getHello; //注入rpc

    public String sayHello(){
        return getHello.sayHello(); // 提供一个hello World
    }
}
