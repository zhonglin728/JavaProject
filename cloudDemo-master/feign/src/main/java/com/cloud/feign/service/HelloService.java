package com.cloud.feign.service;

import com.cloud.feign.entity.User;
import com.cloud.feign.rpc.GetHello;
import com.cloud.feign.rpc.GitHubApi;
import com.cloud.feign.rpc.LayApi;
import com.cloud.feign.service.inter.IHelloServiceInter;
import com.sun.javaws.security.AppContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class HelloService implements IHelloServiceInter {
    @Autowired
    private GetHello getHello; //注入rpc
    @Autowired
    private LayApi layApi;
   @Autowired
   private GitHubApi gitHubApi;


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
        //Map map1 = layuiApi.getUser("10", "1");
        Map map = new RestTemplate().getForEntity("https://www.layui.com/demo/table/user/?page=2&limit=10", Map.class).getBody();
        return getHello.saveUser(id,userName,userPassword);
    }

    @Override
    public Map saveUserEntity(User user) {
        return getHello.saveUserEntity(user);
    }

    @Override
    public Map gitHubApi(String qq){
        Map map = gitHubApi.searchRepo("a");
        return map;
    }

    @Override
    public Map getLayApi(String page, String limit) {
        Map student = layApi.getLayApi("1", "10");
        return student;
    }
}
