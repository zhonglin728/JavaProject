package com.cloud.feign.service;

import com.cloud.feign.entity.User;
import com.cloud.feign.rpc.GetHello;
import com.cloud.feign.service.inter.IHelloServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HelloService implements IHelloServiceInter {
    @Autowired
    private GetHello getHello; //注入rpc

    @Override
    public String sayHello(){
        return getHello.sayHello(); // 提供一个hello World
    }

    @Override
    public int findInt(int id) {
        return getHello.findInt(id);
    }

    @Override
    public Map saveUser(String id, String userName, String userPassword) {
        return getHello.saveUser(id,userName,userPassword);
    }

    @Override
    public Map saveUserEntity(User user) {
        return getHello.saveUserEntity(user);
    }
}
