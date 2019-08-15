package com.cloud.feign.service.inter;

import com.cloud.feign.entity.User;

import java.util.Map;

public interface IHelloServiceInter {
    String sayHello();

    int findInt(int id);

    Map saveUser(String id, String userName, String userPassword);

    Map saveUserEntity(User user);
}
